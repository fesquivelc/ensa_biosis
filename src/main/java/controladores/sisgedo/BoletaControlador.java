/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores.sisgedo;

import controladores.Controlador;
import dao.DAOSISGEDO;
import entidades.escalafon.Empleado;
import entidades.sisgedo.Boleta;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author RyuujiMD
 */
public class BoletaControlador extends Controlador<Boleta>{
    
    private BoletaControlador() {
        super(Boleta.class, new DAOSISGEDO<Boleta>());
    }
    
    public static BoletaControlador getInstance() {
        return BoletaControladorHolder.INSTANCE;
    }
    
    public List<Boleta> permisoXEmpleado(Empleado empleado){
        String jpql = "SELECT b FROM Boleta b WHERE "
                + "b.usuarioSISGEDO.personaNroDocumento = :empleado";
        Map<String,Object> parametros = new HashMap<>();
        parametros.put("empleado", empleado.getNroDocumento());
        return this.getDao().buscar(jpql, parametros);
    }
    
    private static class BoletaControladorHolder {

        private static final BoletaControlador INSTANCE = new BoletaControlador();
    }
}
