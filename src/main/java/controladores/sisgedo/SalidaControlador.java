/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores.sisgedo;

import com.personal.utiles.FechaUtil;
import controladores.Controlador;
import entidades.escalafon.Empleado;
import entidades.sisgedo.Salida;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author RyuujiMD
 */
public class SalidaControlador extends Controlador<Salida>{
    
    private SalidaControlador() {
        super(Salida.class);
    }
    
    public static SalidaControlador getInstance() {
        return SalidaControladorHolder.INSTANCE;
    }
    
    private static class SalidaControladorHolder {

        private static final SalidaControlador INSTANCE = new SalidaControlador();
    }
    
    public List<Salida> obtenerVacacion(Empleado empleado, Date fecha){
        String jpql = "SELECT v FROM Salida v "
                + "WHERE v.id IN ('9','11') AND v.empleado = :empleado "
                + "AND :fecha BETWEEN v.fechaHoraInicio AND v.fechaHoraFin";
        Map<String, Object> parametros = new HashMap();
        parametros.put("empleado", empleado);
        parametros.put("fecha", empleado);
        
        return this.getDao().buscar(jpql, parametros);
    }
    public List<Salida> obtenerPermiso(Empleado empleado, Date fecha, Date horaDesde, Date horaHasta){
        String jpql = "SELECT p FROM Salida p "
                + "WHERE p.id NOT IN ('9','11') AND p.empleado = :empleado "
                + "AND p.fechaHoraInicio BETWEEN :fechaHoraDesde AND :fechaHoraHasta";
        Map<String, Object> parametros = new HashMap();
        parametros.put("empleado", empleado);
        parametros.put("fechaHoraDesde", FechaUtil.unirFechaHora(fecha, horaDesde));
        parametros.put("fechaHoraDesde", FechaUtil.unirFechaHora(fecha, horaHasta));
        
        return this.getDao().buscar(jpql, parametros);
    }
}
