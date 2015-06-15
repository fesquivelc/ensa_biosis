/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmo;

import com.personal.utiles.FechaUtil;
import controladores.AsignacionHorarioControlador;
import controladores.DetalleJornadaControlador;
import controladores.FeriadoControlador;
import controladores.HorarioControlador;
import controladores.MarcacionControlador;
import controladores.PermisoControlador;
import entidades.AsignacionHorario;
import entidades.DetalleJornada;
import entidades.Feriado;
import entidades.Horario;
import entidades.Marcacion;
import entidades.Turno;
import entidades.escalafon.Empleado;
import entidades.escalafon.RegimenLaboral;
import entidades.reportes.RptAsistenciaDetallado;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

    public List<RptAsistenciaDetallado> analisisDetallado(Date fechaInicio, Date fechaFin, List<Empleado> empleados) {
        List<RptAsistenciaDetallado> asistenciaDetalladaList = new ArrayList<>();
        for (Empleado empleado : empleados) {
            List<AsignacionHorario> asignaciones = asghorc.buscarXEmpleado(empleado, fechaInicio, fechaFin);

            for (AsignacionHorario asignacion : asignaciones) {
                //AQUI CONTRASTAMOS LAS ASIGNACIONES CON LOS CONTRATOS
                
                Date desde = fechaInicio.before(asignacion.getFechaInicio()) ? asignacion.getFechaInicio() : fechaInicio;
                Date hasta = fechaFin.before(asignacion.getFechaFin()) ? fechaFin : asignacion.getFechaFin();

                asistenciaDetalladaList.addAll(this.analizarAsignacion(empleado, asignacion, desde, hasta));
            }
        }
        return asistenciaDetalladaList;
    }

    private List<RptAsistenciaDetallado> analizarAsignacion(Empleado empleado, AsignacionHorario asignacionHorario, Date fechaInicio, Date fechaFin) {
        List<RptAsistenciaDetallado> asistenciaDetalladaList = new ArrayList<>();
        Calendar iterador = Calendar.getInstance();
        iterador.setTime(fechaInicio);

        while (iterador.getTime().compareTo(fechaFin) <= 0) {
            Date fecha = iterador.getTime();
            List<Turno> turnoList = asignacionHorario.getHorario().getTurnoList();
            for (Turno turno : turnoList) {
                List<RptAsistenciaDetallado> asistencia = analizarTurno(empleado, asignacionHorario, turno, fecha);
                if (asistencia != null) {
                    
                    asistenciaDetalladaList.addAll(asistencia);
                }

            }
            iterador.add(Calendar.DATE, 1);
        }

        return asistenciaDetalladaList;
    }

    private List<RptAsistenciaDetallado> analizarTurno(Empleado empleado, AsignacionHorario asignacionHorario, Turno turno, Date fecha) {
        List<RptAsistenciaDetallado> asistenciaDetalladoList = new ArrayList<>();
        Feriado feriado = ferc.buscarXDia(fecha);
        RptAsistenciaDetallado asistenciaDetalle;
        if (feriado != null) {
            asistenciaDetalle = new RptAsistenciaDetallado();
            asistenciaDetalle.setTipoAsistencia("E");
            asistenciaDetalle.setMotivo(feriado.getNombre());
            asistenciaDetalle.setFecha(fecha);
            asistenciaDetalle.setFeriado(feriado);
            asistenciaDetalladoList.add(asistenciaDetalle);
            return asistenciaDetalladoList;

        } else {
            if (isDiaLaboral(fecha, turno)) {

                List<DetalleJornada> detalleJornadaList = dtjornc.buscarXJornada(turno.getJornada());

                char asistenciaResultado = 'R';
                int tardanzaTotal = 0;
                int extraTotal = 0;
                int conteo = 1;
                for (DetalleJornada detalle : detalleJornadaList) {
                    asistenciaDetalle = new RptAsistenciaDetallado();

                    char entradaResultado;
                    char salidaResultado;
                    char detalleResultado = 'R';
                    int entradaTardanza = 0;
                    int salidaExtra = 0;

                    Marcacion entradaMarcacion = marcc.buscarXFechaXhora(empleado, fecha, fecha, detalle.getEntradaDesde(), detalle.getEntradaHasta());
                    Marcacion salidaMarcacion = marcc.buscarXFechaXhora(empleado, fecha, fecha, detalle.getSalidaDesde(), detalle.getSalidaHasta());

                    if (entradaMarcacion == null) {
                        System.out.println("ENTRADA NULL");
                        entradaResultado = 'F';
                    } else {
                        System.out.println("ENTRADA NO NULL");
                        entradaTardanza = this.tardanzaMin(entradaMarcacion.getFechaHora(), FechaUtil.unirFechaHora(fecha, detalle.getEntradaTolerancia()));

                        if (entradaTardanza == 0) {
                            entradaResultado = 'R';
                        } else {
                            entradaResultado = 'T';
                        }
                    }

                    if (salidaMarcacion == null) {
                        System.out.println("ENTRADA NULL");
                        salidaResultado = 'F';
                    } else {
                        System.out.println("ENTRADA NO NULL");
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

                    if (asistenciaResultado == 'R' && detalleResultado == 'R') {
                        asistenciaResultado = 'R';
                    } else if (asistenciaResultado == 'F' || detalleResultado == 'F') {
                        asistenciaResultado = 'F';
                    } else if (asistenciaResultado == 'T' || detalleResultado == 'T') {
                        asistenciaResultado = 'T';
                    }

                    tardanzaTotal += entradaTardanza;
                    extraTotal += salidaExtra;

                    asistenciaDetalle.setInicio(entradaResultado != 'F' ? entradaMarcacion.getFechaHora() : null);
                    asistenciaDetalle.setFin(salidaResultado != 'F' ? salidaMarcacion.getFechaHora() : null);

                    asistenciaDetalle.setTipoAsistencia(detalleResultado + "");
                    asistenciaDetalle.setMinutosExtra(detalleResultado != 'F' ? salidaExtra : 0);
                    asistenciaDetalle.setMinutosTardanza(detalleResultado != 'F' ? entradaTardanza : 0);
                    asistenciaDetalle.setEmpleado(empleado);
                    asistenciaDetalle.setFecha(fecha);
                    RegimenLaboral regimen = empleado.getFichaLaboral().getRegimenLaboral();
                    asistenciaDetalle.setRegimenLaboral(regimen == null ? "SIN ASIGNAR" : regimen.getNombre());
                    asistenciaDetalle.setAsignacionHorario(asignacionHorario);

                    asistenciaDetalladoList.add(asistenciaDetalle);
                }//FIN DEL FOR

                return asistenciaDetalladoList;

            } else {
                return null;
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
}
