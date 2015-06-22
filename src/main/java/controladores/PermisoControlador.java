/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidades.AsignacionPermiso;
import entidades.Permiso;
import entidades.escalafon.Empleado;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author fesquivelc
 */
public class PermisoControlador extends Controlador<Permiso>{

    public PermisoControlador() {
        super(Permiso.class);
    }

    @Override
    public void prepararCrear() {
        super.prepararCrear(); //To change body of generated methods, choose Tools | Templates.
        this.getSeleccionado().setAsignacionPermisoList(new ArrayList<AsignacionPermiso>());
    }
    
    public List<Permiso> buscarXEmpleadoXFechaEntreHora(Empleado empleado, Date fecha, Date horaInicio, Date horaFin){
        String jpql = "SELECT p FROM Permiso p WHERE "
                + "p.porFecha = FALSE AND "
                + "p.fechaInicio = :fecha AND "
//                + "(:horaInicio BETWEEN p.horaInicio AND p.horaFin) AND "
                + "EXISTS(SELECT a FROM AsignacionPermiso a WHERE a.empleado = :empleado AND a.permiso = p) "
                + "ORDER BY p.horaInicio ASC";
        
        Map<String, Object> variables = new HashMap();
        variables.put("empleado", empleado);
        variables.put("fecha", fecha);
//        variables.put("horaInicio", horaInicio);
//        variables.put("horaFin", horaFin);
        
        return this.getDao().buscar(jpql, variables);
    }
    
    public Permiso buscarXEmpleadoXFecha(Empleado empleado, Date fecha){
        String jpql = "SELECT p FROM Permiso p WHERE "
                + "p.porFecha = TRUE AND "
                + ":fecha BETWEEN p.fechaInicio AND p.fechaFin  AND "
//                + "(:horaInicio BETWEEN p.horaInicio AND p.horaFin) AND "
                + "EXISTS(SELECT a FROM AsignacionPermiso a WHERE a.empleado = :empleado AND a.permiso = p) "
                + "ORDER BY p.horaInicio ASC";
        
        Map<String, Object> variables = new HashMap();
        variables.put("empleado", empleado);
        variables.put("fecha", fecha);
//        variables.put("horaInicio", horaInicio);
//        variables.put("horaFin", horaFin);
        List<Permiso> permisos = this.getDao().buscar(jpql, variables);
        
        return permisos.isEmpty() ? null : permisos.get(0);
    }
    
}
