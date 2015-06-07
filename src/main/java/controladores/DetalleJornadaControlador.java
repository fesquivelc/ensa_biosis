/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidades.DetalleJornada;
import entidades.Jornada;
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
    
    public static DetalleJornadaControlador getInstance() {
        return DetalleJornadaControladorHolder.INSTANCE;
    }
    
    private static class DetalleJornadaControladorHolder {

        private static final DetalleJornadaControlador INSTANCE = new DetalleJornadaControlador();
    }
}
