/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import algoritmo.AnalisisAsistencia;
import com.personal.utiles.FechaUtil;
import controladores.EmpleadoControlador;
import controladores.biostar.EventoControlador;
import entidades.AsignacionHorario;
import entidades.DetalleJornada;
import entidades.Horario;
import entidades.Turno;
import entidades.biostar.Evento;
import entidades.escalafon.Empleado;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 *
 * @author RyuujiMD
 */
public class PruebaMarcacion {

    /**
     * @param args the command line arguments
     */
    public List<Evento> getMarcaciones(Empleado empleado, Date fechaInicio, Date fechaFin, Horario horario) {
        Date fecha = new Date();
        Calendar cal = Calendar.getInstance();
        List<Evento> eventos = new ArrayList<>();
        while (fechaInicio.compareTo(fechaFin) <= 0) {
            System.out.println(String.format("BUCLE FECHA INICIO: %s FECHA FIN %s", fechaInicio, fechaFin));
            fecha.setTime(fechaInicio.getTime());

            List<Turno> turnos = horario.getTurnoList();

            for (Turno turno : turnos) {
                if (isLaboral(fecha, turno)) {
                    List<DetalleJornada> detalles = turno.getJornada().getDetalleJornadaList();

                    for (DetalleJornada detalle : detalles) {
                        eventos.addAll(generarMarcaciones(empleado, fecha, detalle));
                    }
                }
            }

            cal.setTime(fechaInicio);
            cal.add(Calendar.DATE, 1);
            fechaInicio.setTime(cal.getTimeInMillis());
        }
        System.out.println(String.format("TAMAÑO DE LOS EVENTOS %s", eventos.size()));
        return eventos;
    }

    public static void main(String[] args) {
        // TODO code application logic here
        AnalisisAsistencia aa = new AnalisisAsistencia();
        EmpleadoControlador ec = new EmpleadoControlador();
        PruebaMarcacion pc = new PruebaMarcacion();
        EventoControlador evc = EventoControlador.getInstance();
        List<Empleado> empleados = ec.buscarTodos();

        for (Empleado empleado : empleados) {
            if (!empleado.getNroDocumento().equals("16527803")) {
                List<AsignacionHorario> asignacionHorarios = aa.obtenerHorarios(empleado);

                List<Evento> eventos = null;

                for (AsignacionHorario asignacion : asignacionHorarios) {
                    Date fechaInicio = asignacion.getFechaInicio();
                    Date fechaFin = asignacion.getFechaFin();
                    System.out.println(String.format("EMPLEADO %s - HORARIO %s - FECHA INICIO %s - FECHA FIN %s", empleado.getNombreCompleto(), asignacion.getHorario().getCodigo(), fechaInicio, fechaFin));
                    eventos = pc.getMarcaciones(empleado, fechaInicio, fechaFin, asignacion.getHorario());
                }
                if (eventos != null) {
                    for (Evento evento : eventos) {
                        evc.guardar(evento);
                    }
                }
            }

        }

        System.exit(0);

    }

    private boolean isLaboral(Date fecha, Turno turno) {
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

    private List<Evento> generarMarcaciones(Empleado empleado, Date fecha, DetalleJornada detalle) {
        List<Evento> marcaciones = new ArrayList<>();

        Calendar cal = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal.setTime(fecha);

        Date entrada = FechaUtil.unirFechaHora(cal.getTime(), detalle.getEntrada());

        Date entradaTolerancia = FechaUtil.unirFechaHora(cal.getTime(), detalle.getEntradaTolerancia());
        System.out.println("-----------------------------------------------------------------");
        System.out.println(String.format("ENTRADA: %s ENTRADA TOLERANCIA: %s", entrada, entradaTolerancia));
        if (detalle.isSalidaDiaSiguiente()) {
            cal.add(Calendar.DATE, 1);
        }

        Date fechaFin = cal.getTime();

        Date salida = FechaUtil.unirFechaHora(fechaFin, detalle.getSalida());
        cal2.setTime(salida);
        cal2.add(Calendar.MINUTE, 5);
        Date salidaHasta = FechaUtil.unirFechaHora(fechaFin, cal2.getTime());

        System.out.println(String.format("SALIDA: %s SALIDA HASTA: %s", salida, salidaHasta));
        System.out.println("-----------------------------------------------------------------");
        Evento eventoEntrada = new Evento();
        Evento eventoSalida = new Evento();

        eventoEntrada.setEmpleadoNroDocumento(Integer.parseInt(empleado.getNroDocumento()));
        eventoEntrada.setFechaHora(this.randomDate(entrada, entradaTolerancia));

        eventoSalida.setEmpleadoNroDocumento(Integer.parseInt(empleado.getNroDocumento()));
        eventoSalida.setFechaHora(this.randomDate(salida, salidaHasta));

        System.out.println(String.format("EVENTO ENTRADA: %s \n EVENTO SALIDA %s", eventoEntrada.toString(), eventoSalida.toString()));

        marcaciones.add(eventoSalida);
        marcaciones.add(eventoEntrada);

        return marcaciones;
    }

    private Date randomDate(Date inicio, Date fin) {
        return new Date(this.random(inicio.getTime(), fin.getTime()));
    }

    private long random(long inicio, long fin) {
        Random r = new Random();
        return r.nextInt((int) fin - (int) inicio) + inicio;
    }

}
