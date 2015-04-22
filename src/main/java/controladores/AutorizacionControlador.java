/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidades.Autorizacion;
import entidades.escalafon.Empleado;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author fesquivelc
 */
public class AutorizacionControlador extends Controlador<Autorizacion>{
    
    private AutorizacionControlador() {
        super(Autorizacion.class);
    }
    
    public static AutorizacionControlador getInstance() {
        return AutorizacionControladorHolder.INSTANCE;
    }

    public int contarXFecha(Date fechaInicio, Date fechaFin) {
        String jpql = "SELECT COUNT(a) FROM Autorizacion a WHERE a.fecha BETWEEN :fechaI AND :fechaF";
        Map<String,Object> map = new HashMap();
        map.put("fechaI", fechaInicio);
        map.put("fechaF", fechaFin);
        
        return this.getDao().contar(jpql, map);
    }

    public int contarXEmpleadoXFecha(Empleado empleado, Date fechaInicio, Date fechaFin) {
        String jpql = "SELECT COUNT(a) FROM Autorizacion a WHERE a.empleado = :empleado AND a.fecha BETWEEN :fechaI AND :fechaF";
        Map<String,Object> map = new HashMap();
        map.put("fechaI", fechaInicio);
        map.put("fechaF", fechaFin);
        map.put("empleado", empleado);
        
        return this.getDao().contar(jpql, map);
    }

    public List<Autorizacion> buscarXFecha(Date fechaInicio, Date fechaFin, int pagina, int tamanio) {
        String jpql = "SELECT a FROM Autorizacion a WHERE a.fecha BETWEEN :fechaI AND :fechaF";
        Map<String,Object> map = new HashMap();
        map.put("fechaI", fechaInicio);
        map.put("fechaF", fechaFin);
        
        return this.getDao().buscar(jpql, map, pagina, tamanio);
    }

    public List<Autorizacion> buscarXEmpleadoXFecha(Empleado empleado, Date fechaInicio, Date fechaFin, int pagina, int tamanio) {
        String jpql = "SELECT a FROM Autorizacion a WHERE a.empleado = :empleado AND a.fecha BETWEEN :fechaI AND :fechaF";
        Map<String,Object> map = new HashMap();
        map.put("fechaI", fechaInicio);
        map.put("fechaF", fechaFin);
        map.put("empleado", empleado);
        
        return this.getDao().buscar(jpql, map, pagina, tamanio);
    }
    
    private static class AutorizacionControladorHolder {

        private static final AutorizacionControlador INSTANCE = new AutorizacionControlador();
    }
}
