/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import algoritmo.AnalisisAsistencia;
import entidades.Horario;
import entidades.Turno;
import entidades.escalafon.Empleado;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author fesquivelc
 */
public class TurnoControlador extends Controlador<Turno>{
    private final AnalisisAsistencia analisis = new AnalisisAsistencia();
    private final HorarioControlador hc = new HorarioControlador();
    
    private TurnoControlador() {
        super(Turno.class);
    }
    
    public static TurnoControlador getInstance() {
        return TurnoControladorHolder.INSTANCE;
    }

//    public List<Turno> buscarXEmpleado(Empleado empleadoSeleccionado) {
//        List<Horario> horarios = analisis.obtenerHorarios(empleadoSeleccionado);
//        String jpql = "SELECT t FROM Turno t WHERE t.horario IN :horarios";
//        Map<String,Object> map = new HashMap();
//        map.put("horarios", horarios);
//        
//        return this.getDao().buscar(jpql, map);
//    }
    
    private static class TurnoControladorHolder {

        private static final TurnoControlador INSTANCE = new TurnoControlador();
    }
}
