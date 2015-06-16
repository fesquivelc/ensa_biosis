/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidades.escalafon.Contrato;
import entidades.escalafon.Empleado;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Administrador
 */
public class ContratoControlador extends Controlador<Contrato>{
 
    public ContratoControlador(){
        super(Contrato.class);
    }
    
    
    public List<Contrato> buscarXNombrexFechaASC(Empleado empleado){
        String jpql = "SELECT c FROM Contrato c WHERE c.empleado = :empleado "
                    + " ORDER BY c.fechaInicio ASC";
        
        Map<String,Object> param = new HashMap<>();
        param.put("empleado", empleado);
        
        return this.getDao().buscar(jpql, param);
    }
}
