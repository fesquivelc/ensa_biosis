/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidades.escalafon.Empleado;
import entidades.escalafon.AreaEmpleado;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Documentos
 */
public class AreaEmpleadoControlador extends Controlador<AreaEmpleado> {

    public AreaEmpleadoControlador() {
        super(AreaEmpleado.class);
    }

    public List<AreaEmpleado> buscarXNombrexFechaASC(Empleado empleado){
        String jpql = "SELECT e FROM AreaEmpleado e WHERE e.empleado = :empleado "
                    + " ORDER BY e.fechaInicio ASC";
        
        Map<String,Object> param = new HashMap<>();
        param.put("empleado", empleado);
        
        return this.getDao().buscar(jpql, param);
    }
    
    public List<AreaEmpleado> buscarXNombrexFechaxVigente(Empleado empleado, Date fechaInicio, Date fechaFin, Boolean flag){
        String jpql = "SELECT e FROM EmpleadoArea e WHERE e.empleado = :empleado "
                    + "AND e.fechaInicio between :fechaInicio and :fechaFin"
                    + "AND e.vigente = :flag";
        
        Map<String,Object> param = new HashMap<>();
        param.put("empleado", empleado);
        param.put("fechaInicio", fechaInicio);
        param.put("fechaFin", fechaFin);
        param.put("flag", flag);
        
        return this.getDao().buscar(jpql, param);
    }
}
