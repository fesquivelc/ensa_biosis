/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidades.DetalleJornada;
import entidades.Jornada;
import entidades.escalafon.Empleado;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author RyuujiMD
 */
public class DetalleJornadaControlador extends Controlador<DetalleJornada>{
    
    private DetalleJornadaControlador() {
        super(DetalleJornada.class);
    }
    
    public List<DetalleJornada> buscarXJornada(Jornada jornada){
        String jpql = "SELECT d FROM DetalleJornada d WHERE d.jornada = :jornada ORDER BY d.entrada";
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("jornada", jornada);
        return this.getDao().buscar(jpql, parametros);
    }
    
    public List<DetalleJornada> buscarXEmpleado(Empleado empleado, Date fecha){
        String jpql = "SELECT d FROM DetalleJornada d WHERE "
                + "EXISTS(SELECT t FROM Turno t WHERE t.jornada = d.jornada AND "
                + "EXISTS(SELECT asg FROM AsignacionHorario asg WHERE :fecha BETWEEN asg.fechaInicio AND asg.fechaFin AND asg.horario = t.horario AND (asg.empleado = :empleado OR "
                + "EXISTS(SELECT dgh FROM DetalleGrupoHorario dgh WHERE asg.grupoHorario = dgh.grupoHorario AND dgh.empleado = :empleado)"
                + "))"
                + ") "
                + "ORDER BY d.entrada ASC";
        Map<String, Object> variables = new HashMap();
        variables.put("empleado", empleado);
        variables.put("fecha", fecha);
        return this.getDao().buscar(jpql, variables);
    }
    
    public static DetalleJornadaControlador getInstance() {
        return DetalleJornadaControladorHolder.INSTANCE;
    }
    
    private static class DetalleJornadaControladorHolder {

        private static final DetalleJornadaControlador INSTANCE = new DetalleJornadaControlador();
    }
}
