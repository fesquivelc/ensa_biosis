/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmo;

import com.personal.utiles.FechaUtil;
import controladores.AreaEmpleadoControlador;
import controladores.AsignacionHorarioControlador;
import controladores.AsignacionPermisoControlador;
import controladores.AutorizacionControlador;
import controladores.ContratoControlador;
import controladores.DetalleJornadaControlador;
import controladores.FeriadoControlador;
import controladores.HorarioControlador;
import controladores.MarcacionControlador;
import controladores.PermisoControlador;
import controladores.VacacionControlador;
import controladores.sisgedo.BoletaControlador;
import entidades.AsignacionHorario;
import entidades.Autorizacion;
import entidades.DetalleJornada;
import entidades.Feriado;
import entidades.Marcacion;
import entidades.Permiso;
import entidades.Turno;
import entidades.Vacacion;
import entidades.escalafon.AreaEmpleado;
import entidades.escalafon.Contrato;
import entidades.escalafon.Empleado;
import entidades.reportes.RptAsistenciaDetallado;
import entidades.sisgedo.Boleta;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RyuujiMD
 */
public class AnalisisAsistenciaCaliente {

    private final HorarioControlador horc = new HorarioControlador();
    private final AsignacionHorarioControlador asghorc = new AsignacionHorarioControlador();
    private final FeriadoControlador ferc = new FeriadoControlador();
    private final PermisoControlador permc = new PermisoControlador();
    private final DetalleJornadaControlador dtjornc = DetalleJornadaControlador.getInstance();
    private final MarcacionControlador marcc = new MarcacionControlador();
    private final ContratoControlador contc = ContratoControlador.getInstance();
    private final AutorizacionControlador autc = AutorizacionControlador.getInstance();
    private final AsignacionPermisoControlador asgpermc = new AsignacionPermisoControlador();
    private final VacacionControlador vacc = new VacacionControlador();
    private final AreaEmpleadoControlador aperc = new AreaEmpleadoControlador();
    private final BoletaControlador bolc = BoletaControlador.getInstance();

    private List<Empleado>[] obtenerLimites(List<Empleado> empleados) {
        List<Empleado>[] limites = new List[4];
        int segmentos = 4;

        limites[0] = new ArrayList<>();
        limites[1] = new ArrayList<>();
        limites[2] = new ArrayList<>();
        limites[3] = new ArrayList<>();

        int contador = 0;
        for (Empleado empleado : empleados) {
            limites[contador].add(empleado);
            if (contador < 3) {
                contador++;
            } else {
                contador = 0;
            }
        }

        return limites;
    }

//    private RptAsistenciaDetallado analizarPermisoSISGEDO(Empleado empleado, Contrato contrato, Boleta bol, DetalleJornada detalle, Date fecha) {
//        Calendar cal = Calendar.getInstance();
//
//        RptAsistenciaDetallado asistenciaPermiso = new RptAsistenciaDetallado();
//        cal.setTime(FechaUtil.soloHora(bol.getSalidaFechaHora()));
//        cal.add(Calendar.MINUTE, 40);
//        Date permisoInicioHasta = cal.getTime();
//        cal.add(Calendar.MINUTE, 5);
//        Date permisoFinDesde = cal.getTime();
//
//        Marcacion permisoInicio = marcc.buscarXFechaXhora(empleado, fecha, fecha, FechaUtil.soloHora(), permisoInicioHasta);
//        Marcacion permisoFin = marcc.buscarXFechaXhora(empleado, fecha, fecha, permisoFinDesde, detalle.getSalidaHasta());
//
//        asistenciaPermiso.setInicio(permisoInicio == null ? null : permisoInicio.getFechaHora());
//        asistenciaPermiso.setFin(permisoFin == null ? null : permisoFin.getFechaHora());
//        asistenciaPermiso.setTipoDetalle("P");
//        asistenciaPermiso.setTipoAsistencia("P");
//        asistenciaPermiso.setEmpleado(empleado);
//        asistenciaPermiso.setFecha(fecha);
//        asistenciaPermiso.setPermiso(permiso);
//        asistenciaPermiso.setDetalleJornada(detalle);
//        asistenciaPermiso.setRegimenLaboral(contrato.getRegimenLaboral() == null ? "" : contrato.getRegimenLaboral().getNombre());
//        return asistenciaPermiso;
//    }
    private class HiloAnalisis extends Thread {

        private List<Empleado> empleadoList;
        private List<RptAsistenciaDetallado> asistenciaDetalladoList;
        private Date fechaInicio;
        private Date fechaFin;
        private int numeroHilo;

        public HiloAnalisis(List<Empleado> empleadoList, List<RptAsistenciaDetallado> asistenciaDetalladoList, Date fechaInicio, Date fechaFin) {
            this.empleadoList = empleadoList;
            this.asistenciaDetalladoList = asistenciaDetalladoList;
            this.fechaInicio = fechaInicio;
            this.fechaFin = fechaFin;
        }

        public int getNumeroHilo() {
            return numeroHilo;
        }

        public void setNumeroHilo(int numeroHilo) {
            this.numeroHilo = numeroHilo;
        }

        @Override
        public void run() {

            List<RptAsistenciaDetallado> analisis = analisisDetallado(fechaInicio, fechaFin, empleadoList);
            System.out.println("ANALISIS TAMANIO: " + analisis.size());
            this.asistenciaDetalladoList.addAll(analisis);
            System.out.println("TERMINANDO HILO " + getNumeroHilo());
        }

    }

    public List<RptAsistenciaDetallado> iniciarAnalisis(Date fechaInicio, Date fechaFin, List<Empleado> empleados) {
        List<RptAsistenciaDetallado> asistenciaDetalladaList = new ArrayList<>();

        List<Empleado>[] division = obtenerLimites(empleados);
        HiloAnalisis tareas[] = new HiloAnalisis[4];
        int conteo = 0;
        for (List<Empleado> empleadoList : division) {
            if (!empleadoList.isEmpty()) {
                System.out.println("INICIANDO HILO " + conteo + " TAMAÑO " + empleadoList.size() + " " + fechaInicio + " " + fechaFin);
                tareas[conteo] = new HiloAnalisis(empleadoList, asistenciaDetalladaList, fechaInicio, fechaFin);
                tareas[conteo].setNumeroHilo(conteo);
                tareas[conteo].start();
                conteo++;
            }
        }

        for (Thread tarea : tareas) {
            try {
                if (tarea != null) {
                    tarea.join();
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(AnalisisAsistenciaCaliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        System.out.println("SE TERMINARON LAS TAREAS");

        return asistenciaDetalladaList;
    }

    public List<RptAsistenciaDetallado> analisisDetallado(Date fechaInicio, Date fechaFin, List<Empleado> empleados) {
        List<RptAsistenciaDetallado> asistenciaDetalladaList = new ArrayList<>();
        for (Empleado empleado : empleados) {
            List<Contrato> contratos = contc.obtenerContratosXFechas(empleado, fechaInicio, fechaFin);

            for (Contrato contrato : contratos) {
                Date desde1 = contrato.getFechaInicio().before(fechaInicio) ? fechaInicio : contrato.getFechaInicio();
                Date hasta1 = contrato.getFechaFin() == null ? fechaFin : contrato.getFechaFin().before(fechaFin) ? contrato.getFechaFin() : fechaFin;
                List<AsignacionHorario> asignaciones
                        = asghorc.buscarXEmpleado(empleado, desde1, hasta1);

                for (AsignacionHorario asignacion : asignaciones) {
                    //AQUI CONTRASTAMOS LAS ASIGNACIONES CON LOS CONTRATOS
                    System.out.println(String.format("EMPLEADO: %s DESDE 1: %s HASTA 1: %s ASIGNACION INICIO: %s ASIGNACION FIN: %s", empleado, desde1, hasta1, asignacion.getFechaInicio(), asignacion.getFechaFin()));
                    Date desde2 = desde1.before(asignacion.getFechaInicio()) ? asignacion.getFechaInicio() : desde1;
                    Date hasta2 = hasta1.before(asignacion.getFechaFin()) ? hasta1 : asignacion.getFechaFin();

                    asistenciaDetalladaList.addAll(this.analizarAsignacion(empleado, contrato, asignacion, desde2, hasta2));
                }
            }

        }
        return asistenciaDetalladaList;
    }

    private List<RptAsistenciaDetallado> analizarAsignacion(Empleado empleado, Contrato contrato, AsignacionHorario asignacionHorario, Date fechaInicio, Date fechaFin) {
        List<RptAsistenciaDetallado> asistenciaDetalladaList = new ArrayList<>();
        Calendar iterador = Calendar.getInstance();
        iterador.setTime(fechaInicio);

        while (iterador.getTime().compareTo(fechaFin) <= 0) {
            Date fecha = iterador.getTime();
            List<Turno> turnoList = asignacionHorario.getHorario().getTurnoList();
            for (Turno turno : turnoList) {
                List<RptAsistenciaDetallado> asistencia = analizarTurno(empleado, contrato, asignacionHorario, turno, fecha);
                if (asistencia != null) {
                    asistenciaDetalladaList.addAll(asistencia);
                }
            }
            iterador.add(Calendar.DATE, 1);
        }

        return asistenciaDetalladaList;
    }

    private List<RptAsistenciaDetallado> analizarTurno(Empleado empleado, Contrato contrato, AsignacionHorario asignacionHorario, Turno turno, Date fecha) {
        List<RptAsistenciaDetallado> asistenciaDetalladoList = new ArrayList<>();
        AreaEmpleado areaEmpleado = aperc.buscarXEmpleadoXFecha(empleado, fecha);
        RptAsistenciaDetallado asistenciaDetalle;
        Vacacion vacacion = vacc.buscarXDia(empleado, fecha);
        if (vacacion != null) {
            asistenciaDetalle = new RptAsistenciaDetallado();
            asistenciaDetalle.setTipoAsistencia("V");
            asistenciaDetalle.setMotivo(vacacion.getDocumento());
            asistenciaDetalle.setFecha(fecha);
            asistenciaDetalle.setVacacion(vacacion);
            asistenciaDetalle.setEmpleado(empleado);
            asistenciaDetalle.setAsignacionHorario(asignacionHorario);
            asistenciaDetalle.setArea(areaEmpleado == null ? null : areaEmpleado.getDepartamento());
            asistenciaDetalle.setContrato(contrato);
            asistenciaDetalladoList.add(asistenciaDetalle);

            return asistenciaDetalladoList;
        } else {
            Feriado feriado = ferc.buscarXDia(fecha);
            if (feriado != null) {
                asistenciaDetalle = new RptAsistenciaDetallado();
                asistenciaDetalle.setTipoAsistencia("E");
                asistenciaDetalle.setMotivo(feriado.getNombre());
                asistenciaDetalle.setFecha(fecha);
                asistenciaDetalle.setFeriado(feriado);
                asistenciaDetalle.setEmpleado(empleado);
                asistenciaDetalle.setAsignacionHorario(asignacionHorario);
                asistenciaDetalle.setArea(areaEmpleado == null ? null : areaEmpleado.getDepartamento());
                asistenciaDetalladoList.add(asistenciaDetalle);
                asistenciaDetalle.setContrato(contrato);
                return asistenciaDetalladoList;

            } else {
                if (isDiaLaboral(fecha, turno)) {
                    Permiso permisoXFecha = permc.buscarXEmpleadoXFecha(empleado, fecha);
                    if (permisoXFecha != null) {
                        asistenciaDetalle = new RptAsistenciaDetallado();
                        asistenciaDetalle.setTipoAsistencia("P");
                        asistenciaDetalle.setMotivo(permisoXFecha.getMotivo());
                        asistenciaDetalle.setFecha(fecha);
                        asistenciaDetalle.setPermiso(permisoXFecha);
                        asistenciaDetalle.setEmpleado(empleado);
                        asistenciaDetalle.setContrato(contrato);
                        asistenciaDetalle.setAsignacionHorario(asignacionHorario);
                        asistenciaDetalle.setArea(areaEmpleado == null ? null : areaEmpleado.getDepartamento());
                        asistenciaDetalladoList.add(asistenciaDetalle);
                        return asistenciaDetalladoList;
                    } else {
                        Boleta boleta = bolc.permisoXFechaXEmpleado(empleado, fecha);

                        if (boleta != null) {

                            asistenciaDetalle = new RptAsistenciaDetallado();
                            asistenciaDetalle.setTipoAsistencia("SP");
                            asistenciaDetalle.setMotivo(boleta.getDescripcion());
                            asistenciaDetalle.setFecha(fecha);
                            asistenciaDetalle.setBoleta(boleta);
                            asistenciaDetalle.setEmpleado(empleado);
                            asistenciaDetalle.setContrato(contrato);
                            asistenciaDetalle.setAsignacionHorario(asignacionHorario);
                            asistenciaDetalle.setArea(areaEmpleado == null ? null : areaEmpleado.getDepartamento());
                            asistenciaDetalladoList.add(asistenciaDetalle);
                            return asistenciaDetalladoList;
                        } else {
                            List<DetalleJornada> detalleJornadaList = dtjornc.buscarXJornada(turno.getJornada());

                            char asistenciaResultado = 'R';
                            int tardanzaTotal = 0;
                            int extraTotal = 0;
                            int conteo = 1;
                            for (DetalleJornada detalle : detalleJornadaList) {
                                //Se quitan los permisos x horas de la lista principal de los reportes x peticion del GRP.
                                
//                            System.out.println("FECHA ENTRADA SALIDA " + fecha + " " + detalle.getEntrada() + " " + detalle.getSalida());
//                                List<Permiso> permisoList = permc.buscarXEmpleadoXFechaEntreHora(empleado, fecha, detalle.getEntradaDesde(), detalle.getSalida());
                                //PERMISOS X HORAS
                                
//                                System.out.println("DATOS BOLETA: " + empleado.getNombreCompleto() + " - " + fecha + " - " + detalle.getEntradaDesde() + "--" + detalle.getSalida());
//                                List<Boleta> boletaList = bolc.permisoXHoraXFecha(empleado, fecha, detalle.getEntradaDesde(), detalle.getSalida());
//                                System.out.println("PERMISOS: " + permisoList.size());
//                                System.out.println("PERMISOS BOLETA: " + boletaList.size());
//                                for (Permiso permiso : permisoList) {
//                                    if (permiso.getHoraInicio().compareTo(detalle.getEntradaDesde()) >= 0 && permiso.getHoraFin().compareTo(detalle.getSalidaHasta()) <= 0) {
////                                    System.out.println("PERMISO LIST");
//                                        RptAsistenciaDetallado asistenciaPermiso = analizarPermiso(empleado, contrato, permiso, detalle, fecha);
//                                        asistenciaPermiso.setAsignacionHorario(asignacionHorario);
//                                        asistenciaPermiso.setContrato(contrato);
//                                        asistenciaDetalladoList.add(asistenciaPermiso);
//                                    }
//                                }

//                                for (Boleta bol : boletaList) {
//                                    asistenciaDetalle = new RptAsistenciaDetallado();
////                                    asistenciaDetalle.setInicio(permisoInicio == null ? null : permisoInicio.getFechaHora());
////                                    asistenciaDetalle.setFin(permisoFin == null ? null : permisoFin.getFechaHora());
//                                    asistenciaDetalle.setTipoDetalle("P");
//                                    asistenciaDetalle.setTipoAsistencia("SP");
//                                    asistenciaDetalle.setMotivo(bol.getDescripcion());
//                                    asistenciaDetalle.setFecha(fecha);
//                                    asistenciaDetalle.setBoleta(bol);
//                                    asistenciaDetalle.setEmpleado(empleado);
//                                    asistenciaDetalle.setContrato(contrato);
//                                    asistenciaDetalle.setAsignacionHorario(asignacionHorario);
//                                    asistenciaDetalle.setDetalleJornada(detalle);
////                                    asistenciaDetalle.setRegimenLaboral(contrato.getRegimenLaboral() == null ? "" : contrato.getRegimenLaboral().getNombre());
//                                    asistenciaDetalle.setArea(areaEmpleado == null ? null : areaEmpleado.getDepartamento());
//                                    asistenciaDetalladoList.add(asistenciaDetalle);
////                                    return asistenciaDetalladoList;
//                                }

                                RptAsistenciaDetallado asistencia = analizarDetalle(empleado, contrato, detalle, fecha);
                                asistencia.setAsignacionHorario(asignacionHorario);
                                asistencia.setArea(areaEmpleado == null ? null : areaEmpleado.getDepartamento());
                                asistencia.setRegimenLaboral(null);
                                asistenciaDetalladoList.add(asistencia);

                                char detalleResultado = asistencia.getTipoAsistencia().charAt(0);
                                if (asistenciaResultado == 'R' && detalleResultado == 'R') {
                                    asistenciaResultado = 'R';
                                } else if (asistenciaResultado == 'F' || detalleResultado == 'F') {
                                    asistenciaResultado = 'F';
                                } else if (asistenciaResultado == 'T' || detalleResultado == 'T') {
                                    asistenciaResultado = 'T';
                                }

                                if (!asistencia.getTipoAsistencia().equals("F")) {
                                    tardanzaTotal += asistencia.getMinutosTardanza();
                                    extraTotal += asistencia.getMinutosExtra();
                                }

                            }//FIN DEL FOR

                            return asistenciaDetalladoList;
                        }
                    }

                } else {
                    return null;
                }
            }
        }

    }

    private boolean isDiaLaboral(Date fecha, Turno turno) {
        if (turno.getTipo() == 'S') {
            Calendar cal = Calendar.getInstance();
            cal.setTime(fecha);

            switch (cal.get(Calendar.DAY_OF_WEEK)) {
                case Calendar.MONDAY:
                    return turno.isLunes();
                case Calendar.TUESDAY:
                    return turno.isMartes();
                case Calendar.WEDNESDAY:
                    return turno.isMiercoles();
                case Calendar.THURSDAY:
                    return turno.isJueves();
                case Calendar.FRIDAY:
                    return turno.isViernes();
                case Calendar.SATURDAY:
                    return turno.isSabado();
                case Calendar.SUNDAY:
                    return turno.isDomingo();
                default:
                    return false;
            }
        } else {
            return turno.getFechaInicio().compareTo(fecha) <= 0
                    && turno.getFechaFin().compareTo(fecha) >= 0;
        }
    }

    public int tardanzaMin(Date horaMarcada, Date horaComparar) {
        Long diferencia = (horaMarcada.getTime() - horaComparar.getTime()) / (60 * 1000);
        int diferenciaMin = diferencia.intValue();
        if (diferenciaMin > 0) {
//            System.out.println("MINUTOS: "+Double.parseDouble(diferencia+"")/(1000 * 60));
            return diferenciaMin;
        } else {
            return 0;
        }
    }

    private RptAsistenciaDetallado analizarDetalle(Empleado empleado, Contrato contrato, DetalleJornada detalle, Date fecha) {
        RptAsistenciaDetallado asistenciaDetalle = new RptAsistenciaDetallado();

        char entradaResultado;
        char salidaResultado;
        char detalleResultado = 'R';
        int entradaTardanza = 0;
        int salidaExtra = 0;

        Marcacion entradaMarcacion = marcc.buscarXFechaXhora(empleado, fecha, fecha, detalle.getEntradaDesde(), detalle.getEntradaHasta());
        Marcacion salidaMarcacion = marcc.buscarXFechaXhora(empleado, fecha, fecha, detalle.getSalidaDesde(), detalle.getSalidaHasta());

        if (entradaMarcacion == null) {
//            System.out.println("ENTRADA NULL");
            entradaResultado = 'F';
        } else {
//            System.out.println("ENTRADA NO NULL");
            entradaTardanza = this.tardanzaMin(entradaMarcacion.getFechaHora(), FechaUtil.unirFechaHora(fecha, detalle.getEntradaTolerancia()));

            if (entradaTardanza == 0) {
                entradaResultado = 'R';
            } else {
                entradaResultado = 'T';
            }
        }

        if (salidaMarcacion == null) {
//            System.out.println("ENTRADA NULL");
            salidaResultado = 'F';
        } else {
//            System.out.println("ENTRADA NO NULL");
            salidaResultado = 'R';
            salidaExtra = this.tardanzaMin(salidaMarcacion.getFechaHora(), FechaUtil.unirFechaHora(fecha, detalle.getSalida()));
        }

        if (entradaResultado == 'R' && salidaResultado == 'R') {
            detalleResultado = 'R';
        } else if (entradaResultado == 'T' || salidaResultado == 'T') {
            detalleResultado = 'T';
        } else if (entradaResultado == 'F' || salidaResultado == 'F') {
            detalleResultado = 'F';
        }

        asistenciaDetalle.setInicio(entradaResultado != 'F' ? entradaMarcacion.getFechaHora() : null);
        asistenciaDetalle.setFin(salidaResultado != 'F' ? salidaMarcacion.getFechaHora() : null);
        asistenciaDetalle.setDetalleJornada(detalle);
        asistenciaDetalle.setTipoAsistencia(detalleResultado + "");
        asistenciaDetalle.setMinutosExtra(detalleResultado != 'F' ? salidaExtra : null);
        asistenciaDetalle.setMinutosTardanza(detalleResultado != 'F' ? entradaTardanza : null);

        if (salidaExtra > 0) {
            Autorizacion autorizacion = autc.buscarXDetalleJornadaXFecha(empleado, detalle, fecha);
            if (autorizacion != null) {
                asistenciaDetalle.setMinutosExtraAutorizado(true);
                asistenciaDetalle.setAutorizacionExtra(autorizacion);
            } else {
                asistenciaDetalle.setMinutosExtraAutorizado(false);
            }
        }

        asistenciaDetalle.setEmpleado(empleado);
        asistenciaDetalle.setFecha(fecha);
        asistenciaDetalle.setRegimenLaboral(contrato.getRegimenLaboral() == null ? "" : contrato.getRegimenLaboral().getNombre());
        asistenciaDetalle.setContrato(contrato);
        return asistenciaDetalle;
    }

    private RptAsistenciaDetallado analizarPermiso(Empleado empleado, Contrato contrato, Permiso permiso, DetalleJornada detalle, Date fecha) {
        Calendar cal = Calendar.getInstance();

        RptAsistenciaDetallado asistenciaPermiso = new RptAsistenciaDetallado();
        cal.setTime(permiso.getHoraInicio());
        cal.add(Calendar.MINUTE, 40);
        Date permisoInicioHasta = cal.getTime();
        cal.add(Calendar.MINUTE, 5);
        Date permisoFinDesde = cal.getTime();

        Marcacion permisoInicio = marcc.buscarXFechaXhora(empleado, fecha, fecha, permiso.getHoraInicio(), permisoInicioHasta);
        Marcacion permisoFin = marcc.buscarXFechaXhora(empleado, fecha, fecha, permisoFinDesde, detalle.getSalidaHasta());

        asistenciaPermiso.setInicio(permisoInicio == null ? null : permisoInicio.getFechaHora());
        asistenciaPermiso.setFin(permisoFin == null ? null : permisoFin.getFechaHora());
        asistenciaPermiso.setTipoDetalle("P");
        asistenciaPermiso.setTipoAsistencia("P");
        asistenciaPermiso.setEmpleado(empleado);
        asistenciaPermiso.setFecha(fecha);
        asistenciaPermiso.setPermiso(permiso);
        asistenciaPermiso.setDetalleJornada(detalle);
        asistenciaPermiso.setRegimenLaboral(contrato.getRegimenLaboral() == null ? "" : contrato.getRegimenLaboral().getNombre());
        
        return asistenciaPermiso;
    }
}
