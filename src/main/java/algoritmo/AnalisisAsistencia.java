/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmo;

import controladores.AsignacionHorarioControlador;
import controladores.AsignacionPermisoControlador;

import controladores.DetalleGrupoControlador;
import controladores.FeriadoControlador;
import controladores.MarcacionControlador;
import controladores.RegistroAsistenciaControlador;
import controladores.TCAnalisisControlador;
import controladores.TCSistemaControlador;
import controladores.VacacionControlador;
import entidades.AsignacionHorario;
import entidades.AsignacionPermiso;
import entidades.DetalleGrupoHorario;
import entidades.DetalleRegistroAsistencia;
import entidades.Feriado;
import entidades.GrupoHorario;
import entidades.Horario;
import entidades.Jornada;
import entidades.Marcacion;
import entidades.Permiso;
import entidades.RegistroAsistencia;
import entidades.TCAnalisis;
import entidades.TCSistema;
import entidades.Vacacion;
import com.personal.utiles.FechaUtil;
import controladores.Controlador;
import controladores.DetalleJornadaControlador;
import controladores.PermisoControlador;
import controladores.TipoPermisoControlador;
import entidades.DetalleJornada;
import entidades.Turno;
import entidades.escalafon.Empleado;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author RyuujiMD
 */
public class AnalisisAsistencia {

    private TCAnalisisControlador tcac;
    private FeriadoControlador fc;
    private AsignacionPermisoControlador apc;
    private VacacionControlador vc;
    private MarcacionControlador mc;
    private TCSistemaControlador tcsc;
    private DetalleGrupoControlador dgc;
    private AsignacionHorarioControlador ahc;
    private RegistroAsistenciaControlador rac;
    private PermisoControlador pc;
    private TipoPermisoControlador tpc;
    private static final Logger LOG = Logger.getLogger(AnalisisAsistencia.class.getName());

    private final int MIN_FIN_MARCACION = 500;
    private final int MIN_ANTES_INICIO_PERMISO = 30;

    public AnalisisAsistencia() {
        iniciar();
    }

    private void iniciar() {
        tcac = new TCAnalisisControlador();
        fc = new FeriadoControlador();
        apc = new AsignacionPermisoControlador();
        vc = new VacacionControlador();
        mc = new MarcacionControlador();
        dgc = new DetalleGrupoControlador();
        ahc = new AsignacionHorarioControlador();
        rac = new RegistroAsistenciaControlador();
        tcsc = TCSistemaControlador.getInstance();
        pc = new PermisoControlador();
        tpc = new TipoPermisoControlador();
    }

    private TCAnalisis obtenerPuntoPartida(Empleado empleado) {
        tcac.getDao().getEntityManager().clear();
        TCAnalisis partida = tcac.buscarPorId(empleado.getNroDocumento());
        if (partida == null) {
            partida = new TCAnalisis();
            TCSistema sistema = tcsc.buscarPorId("BIOSIS");

            Date fechaCero = sistema.getFechaCero();
            Date contrato = empleado.getFichaLaboral().getFechaInicio() == null ? fechaCero : empleado.getFichaLaboral().getFechaInicio();

            if (contrato.compareTo(fechaCero) <= 0) {
                partida.setFecha(fechaCero);
                partida.setHora(sistema.getHoraCero());
            } else {
                partida.setFecha(contrato);
                partida.setHora(contrato);
            }
        }
        return partida;
    }

    private TCAnalisis obtenerPuntoLlegada() {
        TCAnalisis llegada = new TCAnalisis();
        Date fechaHoraActual = new Date();
        llegada.setFecha(FechaUtil.soloFecha(fechaHoraActual));
        llegada.setHora(FechaUtil.soloHora(fechaHoraActual));

        return llegada;
    }

    public List<RegistroAsistencia> analizarEmpleados(List<Empleado> empleados) {
        //LA FECHA Y HORA DE FINAL DEL ANALISIS ES LA MISMA DE LA CONSULTA
        List<RegistroAsistencia> registros = new ArrayList<>();
        TCAnalisis llegada = obtenerPuntoLlegada();
        for (Empleado empleado : empleados) {
            //OBTENEMOS LA FECHA Y HORA DE PARTIDA DEL ANALISIS
            TCAnalisis partida = obtenerPuntoPartida(empleado);
            System.out.println(String.format("PUNTOs DE PARTIDA - LLEGADA: %s %s - %s %s", partida.getFecha(), partida.getHora(), llegada.getFecha(), llegada.getHora()));
            //OBTENEMOS LOS HORARIOS DEL EMPLEADO
            List<AsignacionHorario> horarios = obtenerHorarios(empleado);

            //ANALIZAMOS POR HORARIO
            System.out.println("NRO DE HORARIOS: " + horarios.size());
            for (AsignacionHorario asignacionHorario : horarios) {
                System.out.println("POR ANALIZAR HORARIO: " + asignacionHorario.getHorario().getNombre());
//                if(asignacionHorario.getHorario().getTipo() == 'A'){
                registros.addAll(analizarHorario(empleado, asignacionHorario, partida, llegada));
//                }

            }
            if (!registros.isEmpty()) {
                boolean seLogro = true;
                for(RegistroAsistencia reg : registros){
                    System.out.println("REG: "+reg.toString());
                }
                if(rac.guardarLote(registros)){
                    llegada.setEmpleado(empleado.getNroDocumento());
                    this.tcac.modificar(llegada);
                }
                
                
            }

            registros.clear();
        }
        return registros;
    }
    
    private final DetalleJornadaControlador djc = DetalleJornadaControlador.getInstance();

    public List<AsignacionHorario> obtenerHorarios(Empleado empleado) {
        //PRIMERO OBTENEMOS LOS GRUPOS HORARIOS ASIGNADOS

        List<DetalleGrupoHorario> detalles = dgc.buscarXEmpleado(empleado);
        List<GrupoHorario> grupos = new ArrayList<>();
//        List<Horario> horarios = new ArrayList<>();
        List<AsignacionHorario> asignaciones = new ArrayList<>();

        for (DetalleGrupoHorario detalle : detalles) {
            grupos.add(detalle.getGrupoHorario());
        }

        if (!grupos.isEmpty()) {
            asignaciones.addAll(ahc.buscarXGrupos(grupos));
        }

        asignaciones.addAll(ahc.buscarXEmpleado(empleado));

//        if (!asignaciones.isEmpty()) {
//            for (AsignacionHorario asignacion : asignaciones) {
//                horarios.add(asignacion.getHorario());
//            }
//        }
        return asignaciones;
    }

    public List<Horario> obtenerHorarios(Empleado empleado, Date fechaInicio, Date fechaFin) {
        //PRIMERO OBTENEMOS LOS GRUPOS HORARIOS ASIGNADOS

        List<DetalleGrupoHorario> detalles = dgc.buscarXEmpleado(empleado, fechaInicio, fechaFin);
        List<GrupoHorario> grupos = new ArrayList<>();
        List<Horario> horarios = new ArrayList<>();
        List<AsignacionHorario> asignaciones = new ArrayList<>();

        for (DetalleGrupoHorario detalle : detalles) {
            grupos.add(detalle.getGrupoHorario());
        }

        if (!grupos.isEmpty()) {
            asignaciones.addAll(ahc.buscarXGrupos(grupos));
        }

        asignaciones.addAll(ahc.buscarXEmpleado(empleado));

        if (!asignaciones.isEmpty()) {
            for (AsignacionHorario asignacion : asignaciones) {
                horarios.add(asignacion.getHorario());
            }
        }

        return horarios;
    }

    private List<RegistroAsistencia> analizarHorario(Empleado empleado, AsignacionHorario asignacionHorario, TCAnalisis partida, TCAnalisis llegada) {
        List<RegistroAsistencia> registros = new ArrayList<>();
        Calendar cal = Calendar.getInstance();

        Date fInicio = new Date(partida.getFecha().getTime());
        Date fFin = new Date(llegada.getFecha().getTime());

        Date hInicio = new Date(partida.getHora().getTime());
        Date hFin = new Date(llegada.getHora().getTime());

        RegistroAsistencia registro;

        while (fInicio.compareTo(fFin) < 0) {
            //COMAPRAMOS QUE SE ENCUENTRE ENTRE LAS FECHAS DEL HORARIO
            if (fInicio.compareTo(asignacionHorario.getFechaInicio()) >= 0
                    && fInicio.compareTo(asignacionHorario.getFechaFin()) <= 0) {
//                System.out.println("FECHA INICIO: " + fInicio.toString() + " FECHA FIN: " + fFin.toString());
                registro = new RegistroAsistencia();
                registro.setFecha(fInicio);
                registro.setEmpleado(empleado);
                registro.setHorario(asignacionHorario.getHorario());

                AsignacionPermiso permisoXFecha = this.apc.buscarXDia(empleado, fInicio);

                if (permisoXFecha != null) {
                    //SE GUARDA EL REGISTRO COMO UN PERMISO
                    registro.setPermiso(permisoXFecha.getPermiso());
                    registro.setTipoAsistencia('P');
                } else {
                    Vacacion vacacion = this.vc.buscarXDia(empleado, fInicio);

                    if (vacacion != null) {
                        //SE GUARDA EL REGISTRO COMO VACACION
                        registro.setVacacion(vacacion);
                        registro.setTipoAsistencia('V');

                    } else {
                        System.out.println("ANALIZANDO TURNOS");
                        for (Turno turno : asignacionHorario.getHorario().getTurnoList()) {
                            registro = analizarTurno(empleado, turno, fInicio, fInicio, hInicio, fFin, hFin);
                            if (registro != null) {
                                registro.setHorario(asignacionHorario.getHorario());
                                registros.add(registro);
                            }
                        }
                        registro = null;
                    }
                }// FIN DEL ELSE PRINCIPAL

                if (registro != null) {
                    registros.add(registro);
                }
            }

            cal.setTime(fInicio);
            cal.add(Calendar.DAY_OF_MONTH, 1);
            fInicio = cal.getTime();

            cal.setTime(hInicio);
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);

            hInicio = cal.getTime();
        }// FIN DEL WHILE
        System.out.println("NUMERO DE REGISTROS: "+registros.size());
        return registros;
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

    private boolean isOnomastico(Empleado empleado, Date fInicio) {
        Calendar calEmpleado = Calendar.getInstance();
        Calendar calFecha = Calendar.getInstance();

        calEmpleado.setTime(empleado.getFechaNacimiento());
        calFecha.setTime(fInicio);

        calEmpleado.set(Calendar.YEAR, calFecha.get(Calendar.YEAR));

        return calEmpleado.getTime().compareTo(fInicio) == 0;
    }

//    private long tardanza(Date hora, Date horaTolerancia) {
//        long resultado = hora.getTime() - horaTolerancia.getTime();
//        if (resultado < 0) {
//            return Math.abs(resultado);
//        } else {
//            return 0;
//        }
//    }    

    public BigDecimal tardanzaMin(Date horaMarcada, Date horaComparar) {
        Long diferencia = (horaMarcada.getTime() - horaComparar.getTime())/(60*1000);
        int diferenciaMin = diferencia.intValue();
        if (diferenciaMin > 0) {
//            System.out.println("MINUTOS: "+Double.parseDouble(diferencia+"")/(1000 * 60));
            return BigDecimal.valueOf(diferenciaMin);
        } else {
            return BigDecimal.ZERO;
        }
    }

    
//    private DetalleRegistroAsistencia analizarPermisoXHora(Empleado empleado, Jornada jornada, Permiso permiso) {
//        DetalleRegistroAsistencia registroPermiso = new DetalleRegistroAsistencia();
//        registroPermiso.setOrden(2);
//        registroPermiso.setTipoRegistro('P');
//        Calendar cal = Calendar.getInstance();
//        Date horaInicio = null;
//        Date horaFin = null;
//
//        Long diferenciaPermiso = permiso.getHoraFin().getTime() - permiso.getHoraInicio().getTime();
//
//        //VERIFICAMOS SI OCUPA O NO HORA DE ENTRADA U HORA DE SALIDA
//        if (permiso.getHoraInicio().equals(jornada.getTurnoHE())) {
//            horaInicio = jornada.getTurnoHE();
//        } else {
//            //BUSCAMOS MARCACION
//            cal.setTime(permiso.getHoraInicio());
//            cal.add(Calendar.MINUTE, MIN_ANTES_INICIO_PERMISO);
//            Date limiteSuperiorHoraInicio = cal.getTime();
//
//            Marcacion inicioPermiso = mc.buscarXFechaXhora(empleado, permiso.getFechaInicio(), permiso.getHoraInicio(), limiteSuperiorHoraInicio);
//
//            horaInicio = (inicioPermiso == null) ? null : FechaUtil.soloHora(inicioPermiso.getFechaHora());
//        }
//
//        BigDecimal tardanzaEntradaPermiso = null;
//
//        if (horaInicio != null) {
//            cal.setTime(horaInicio);
//            cal.add(Calendar.MINUTE, 1);
//
//            Date limiteInferiorFinPermiso = cal.getTime();
//
//            cal.setTime(horaInicio);
//            cal.add(Calendar.MILLISECOND, diferenciaPermiso.intValue());
//            Date horaEsperadaFinPermiso = cal.getTime();
//
//            Marcacion finPermiso = mc.buscarXFechaXhora(empleado, permiso.getFechaInicio(), limiteInferiorFinPermiso, jornada.getTurnoHS());
//
//            horaFin = (finPermiso == null) ? jornada.getTurnoHS() : FechaUtil.soloHora(finPermiso.getFechaHora());
//
//            tardanzaEntradaPermiso = (horaFin == null) ? null : tardanzaMin(horaFin, horaEsperadaFinPermiso);
//        }
//
//        registroPermiso.setHoraInicio(horaInicio);
//        registroPermiso.setHoraFin(horaFin);
//        registroPermiso.setMinTardanza(tardanzaEntradaPermiso);
//        registroPermiso.setPermiso(permiso);
//        char resultado = ' ';
//        if (horaInicio == null || horaFin == null) {
//            resultado = 'F';
//        } else if (tardanzaEntradaPermiso.compareTo(BigDecimal.ZERO) > 0) {
//            resultado = 'T';
//        } else {
//            resultado = 'R';
//        }
//        registroPermiso.setResultado(resultado);
//
//        return registroPermiso;
//    }
    private Permiso generarPermisoOnomastico(Empleado empleado, Date fInicio) {
        pc.prepararCrear();
        Permiso onomastico = pc.getSeleccionado();

        //CREAMOS LA ASIGNACION
        AsignacionPermiso ap = new AsignacionPermiso();
        ap.setEmpleado(empleado);
        ap.setPermiso(onomastico);

        //
        onomastico.getAsignacionPermisoList().add(ap);
        onomastico.setFechaInicio(fInicio);
        onomastico.setFechaFin(fInicio);
        onomastico.setPorFecha(true);
        onomastico.setTipoPermiso(tpc.buscarPorId("ONO"));
        onomastico.setOpcion('F');
        onomastico.setMotivo("LICENCIA POR ONOMÁSTICO");
        onomastico.setDocumento("LICENCIA POR ONOMÁSTICO");

        long diferencia = onomastico.getFechaFin().getTime() - onomastico.getFechaInicio().getTime();
        BigDecimal diferenciaMin = new BigDecimal(diferencia / (60 * 1000 * 60));
        onomastico.setDiferencia(diferenciaMin);

        if (pc.accion(Controlador.NUEVO)) {
            LOG.info("SE GUARDO EL PERMISO POR ONOMASTICO");
        } else {
            LOG.info("HUBO UN ERROR");
        }

        pc.getDao().getEntityManager();

        return onomastico;
    }

    private RegistroAsistencia analizarTurno(
            Empleado empleado,
            Turno turno,
            Date fInicio,
            Date analisisFechaInicio,
            Date analisisHoraInicio,
            Date analisisFechaFin,
            Date analisisHoraFin) {
        RegistroAsistencia registro = new RegistroAsistencia();
        registro.setEmpleado(empleado);
        registro.setFecha(fInicio);
        Calendar cal = Calendar.getInstance();
        boolean diaLaboral = isDiaLaboral(fInicio, turno);
        if (diaLaboral) {
            //TOMAMOS EN CUENTA QUE SEA FERIADO
            Feriado feriado = this.fc.buscarXDia(fInicio);
            if (feriado != null) {
                //SE REGISTRA COMO FERIADO
                registro.setFeriado(feriado);
                registro.setTipoAsistencia('E'); //RECORDAR QUE E PERTENECE A LOS FERIADOS
            } else {
                //TOMAMOS EN CUENTA EL ONOMASTICO
                if (isOnomastico(empleado, fInicio)) {
                    //SE REGISTRA COMO ONOMASTICO
                    registro.setPermiso(generarPermisoOnomastico(empleado, fInicio));
                    registro.setTipoAsistencia('P');
                } else {
                    //SE PROCEDE AL ANALISIS DE LA JORNADA
                    List<DetalleJornada> detalles = djc.buscarXJornada(turno.getJornada());
                    DetalleJornada ultimo = detalles.get(detalles.size() - 1);
                    System.out.println(String.format("JORNADA: %s ULTIMA HORA: %s", ultimo.getJornada().getNombre(), ultimo.getSalidaHasta()));
                    Date salidaFechaHasta;
                    cal.setTime(fInicio);
                    if (ultimo.isSalidaDiaSiguiente()
                            || ultimo.getSalidaHasta().compareTo(ultimo.getSalidaDesde()) < 0) {
                        cal.add(Calendar.DATE, 1);
                    }
                    salidaFechaHasta = cal.getTime();
                    System.out.println(String.format("FECHA INICIO: %s ANALISIS HORA INICIO: %s FECHA FIN: %s ANALISIS HORA FIN: %s", fInicio,analisisHoraInicio,analisisFechaFin,analisisHoraFin));
                    if (FechaUtil.compararFechaHora(fInicio, analisisHoraInicio, salidaFechaHasta, ultimo.getSalidaHasta()) <= 0
                            && FechaUtil.compararFechaHora(analisisFechaFin, analisisHoraFin, salidaFechaHasta, ultimo.getSalidaHasta()) >= 0) {
                        System.out.println("ANALIZANDO ----");
                        registro = analizarJornada(empleado, turno, fInicio);
                        System.out.println("REGISTRO DE ANALIZAR JORNADA "+registro.toString());
                        return registro;
                    }
                }
            }
        } else if (isOnomastico(empleado, fInicio)) {
            //SE BUSCA EL DIA LABORAL MAS CERCANO PARA ASIGNARLE EL PERMISO POR ONOMASTICO
            //Y SE AGREGA AL REGISTRO
        } else {
            //NO HAY SUCESO SUSCEPTIBLE A REGISTRO
            registro = null;
        }
        return registro;
    }

    private RegistroAsistencia analizarJornada(Empleado empleado, Turno turno, Date fInicio) {
        //PROCEDEMOS A ANALIZAR LOS DETALLES
        List<DetalleJornada> detalles = djc.buscarXJornada(turno.getJornada());
        Calendar calFecha = Calendar.getInstance();
        Calendar calHora = Calendar.getInstance();
        Date entradaFechaDesde = fInicio;
        Date entradaFechaHasta = fInicio;
        Date salidaFechaHasta = fInicio;
        Date salidaFechaDesde = fInicio;
        char registroResultado = 'R';
        
        RegistroAsistencia registro = new RegistroAsistencia();
        registro.setEmpleado(empleado);
        registro.setFecha(fInicio);
        registro.setHorario(turno.getHorario());
        registro.setDetalleRegistroAsistenciaList(new ArrayList<DetalleRegistroAsistencia>());
        
        char entradaResultado = ' ';
        char salidaResultado = ' ';
        BigDecimal tardanzaTotal = BigDecimal.ZERO;
        BigDecimal extraTotal = BigDecimal.ZERO;
        for(DetalleJornada detalle : detalles){
            BigDecimal entradaTardanza = BigDecimal.ZERO;
            BigDecimal salidaExtra = BigDecimal.ZERO;
            calFecha.setTime(fInicio);
            char detalleResultado = ' ';
            if(detalle.getEntradaHasta().compareTo(detalle.getEntradaDesde()) < 0){
                calHora.setTime(detalle.getEntrada());
                if(calHora.get(Calendar.AM_PM) == Calendar.AM){
                    calFecha.add(Calendar.DATE, -1);
                    entradaFechaDesde = calFecha.getTime();
                    entradaFechaHasta = fInicio;
                }else{
                    calFecha.add(Calendar.DATE, 1);
                    entradaFechaDesde = fInicio;
                    entradaFechaHasta = calFecha.getTime();
                }
            }
            
            if(detalle.getSalidaHasta().compareTo(detalle.getSalidaDesde()) < 0){
                calHora.setTime(detalle.getSalida());
                if(calHora.get(Calendar.AM_PM) == Calendar.PM){
                    calFecha.add(Calendar.DATE, 1);
                    salidaFechaDesde = fInicio;
                    salidaFechaHasta = calFecha.getTime();
                }                
            }else if(detalle.isSalidaDiaSiguiente()){
                calFecha.add(Calendar.DATE, 1);
                salidaFechaDesde = calFecha.getTime();
                salidaFechaHasta = calFecha.getTime();
            }
            System.out.println(String.format("EMPLEADO: %s DESDE: %s %s HASTA %s %s", empleado,entradaFechaDesde,detalle.getEntradaDesde(),entradaFechaHasta,detalle.getEntradaHasta()));
            Marcacion entradaMarcacion = mc.buscarXFechaXhora(
                    empleado, 
                    entradaFechaDesde, 
                    entradaFechaHasta, 
                    detalle.getEntradaDesde(),                     
                    detalle.getEntradaHasta()
            );
            System.out.println(String.format("EMPLEADO: %s DESDE: %s %s HASTA %s %s", empleado,entradaFechaDesde,detalle.getSalidaDesde(),salidaFechaHasta,detalle.getSalidaHasta()));
            Marcacion salidaMarcacion = mc.buscarXFechaXhora(
                    empleado, 
                    salidaFechaDesde, 
                    salidaFechaHasta, 
                    detalle.getSalidaDesde(),                     
                    detalle.getSalidaHasta()
            );
            
            if(entradaMarcacion == null){
                System.out.println("ENTRADA NULL");
                entradaResultado = 'F';
            }else{
                System.out.println("ENTRADA NO NULL");
                entradaTardanza = this.tardanzaMin(entradaMarcacion.getFechaHora(), FechaUtil.unirFechaHora(fInicio, detalle.getEntradaTolerancia()));
                
                if(entradaTardanza.equals(BigDecimal.ZERO)){
                    entradaResultado = 'R';
                }else{
                    entradaResultado = 'T';
                } 
            }
            
            if(salidaMarcacion == null){
                System.out.println("ENTRADA NULL");
                salidaResultado = 'F';
            }else{
                System.out.println("ENTRADA NO NULL");
                salidaResultado = 'R';
                salidaExtra = this.tardanzaMin(salidaMarcacion.getFechaHora(), FechaUtil.unirFechaHora(salidaFechaDesde, detalle.getSalida()));
            }
            
            if(entradaResultado == 'R' && salidaResultado == 'R'){
                detalleResultado = 'R';
            }else if(entradaResultado == 'T' || salidaResultado == 'T'){
                detalleResultado = 'T';
            }else if(entradaResultado == 'F' || salidaResultado == 'F'){
                detalleResultado = 'F';
            }
            
            if(registroResultado == 'R' && detalleResultado == 'R'){
                registroResultado = 'R';
            }else if(registroResultado == 'F' || detalleResultado == 'F'){
                registroResultado = 'F';
            }else if(registroResultado == 'T' || detalleResultado == 'T'){
                registroResultado = 'T';
            }
            
            tardanzaTotal.add(entradaTardanza);
            extraTotal.add(salidaExtra);
            
            DetalleRegistroAsistencia detalleRegistro = new DetalleRegistroAsistencia();
            detalleRegistro.setResultado(detalleResultado);
            detalleRegistro.setHoraInicio(entradaMarcacion == null ? null : entradaMarcacion.getFechaHora());
            detalleRegistro.setHoraFin(salidaMarcacion == null ? null : salidaMarcacion.getFechaHora());
            detalleRegistro.setMinExtra(salidaExtra);
            detalleRegistro.setMinTardanza(entradaTardanza);
            detalleRegistro.setRegistroAsistencia(registro);
            detalleRegistro.setOrden(0);
            detalleRegistro.setDetalleJornada(detalle);
            
            registro.getDetalleRegistroAsistenciaList().add(detalleRegistro);
        }
        
        registro.setMinTardanza(tardanzaTotal);
        registro.setMinExtra(extraTotal);
        registro.setTipoAsistencia(registroResultado);
        System.out.println(String.format("ANALIZANDO JORNADA: REGISTRO %s",registro.toString()));
        return registro;
    }

}
