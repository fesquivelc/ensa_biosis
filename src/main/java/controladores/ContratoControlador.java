/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidades.escalafon.Contrato;
import entidades.escalafon.Empleado;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author RyuujiMD
 */
public class ContratoControlador extends Controlador<Contrato>{
    
    private ContratoControlador() {
        super(Contrato.class);
    }
    
    public static ContratoControlador getInstance() {
        return ContratoControladorHolder.INSTANCE;
    }
    
    private static class ContratoControladorHolder {

        private static final ContratoControlador INSTANCE = new ContratoControlador();
    }
    
    public List<Contrato> obtenerContratosXFechas(Empleado empleado, Date fechaInicio, Date fechaFin){
        String jpql = "SELECT c FROM Contrato c "
                + "WHERE "
                + "c.empleado = :empleado AND ("
                + "(c.fechaInicio <= :fechaInicio AND (c.fechaInicio <= :fechaFin OR c.fechaFin IS NULL)) "
                + "OR (c.fechaInicio <= :fechaFin AND (:fechaFin <= c.fechaFin OR c.fechaFin IS NULL))"
                + ") "
                + "ORDER BY c.fechaInicio";
        Map<String,Object> variables = new HashMap();
        variables.put("empleado", empleado);
        variables.put("fechaInicio", fechaInicio);
        variables.put("fechaFin", fechaFin);
        
        return this.getDao().buscar(jpql, variables);
    }
}
