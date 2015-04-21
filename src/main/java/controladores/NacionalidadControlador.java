/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidades.escalafon.Nacionalidad;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author fesquivelc
 */
public class NacionalidadControlador extends Controlador<Nacionalidad>{
    
    private NacionalidadControlador() {
        super(Nacionalidad.class);
    }
    
    public List<Nacionalidad> buscarXDescripcion(String patron){
        String jpql = "SELECT n FROM Nacionalidad n WHERE n.descripcion LIKE CONCAT('%',:patron,'%')";
        Map<String, Object> map = new HashMap();
        map.put("patron", patron);
        return this.getDao().buscar(jpql, map);
    }
    
    public static NacionalidadControlador getInstance() {
        return NacionalidadControladorHolder.INSTANCE;
    }
    
    private static class NacionalidadControladorHolder {

        private static final NacionalidadControlador INSTANCE = new NacionalidadControlador();
    }
}
