/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidades.escalafon.Ubigeo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author fesquivelc
 */
public class UbigeoControlador extends Controlador<Ubigeo>{
    
    private UbigeoControlador() {
        super(Ubigeo.class);
    }
    
    public List<Ubigeo> buscarUbigeo(String ubigeo){
        String jpql = "SELECT u FROM Ubigeo u "
                + "WHERE "
                + "u.departamento LIKE CONCAT('%',:ubigeo,'%') OR "
                + "u.provincia LIKE CONCAT('%',:ubigeo,'%') OR "
                + "u.distrito LIKE CONCAT('%',:ubigeo,'%')";
        Map<String, Object> map = new HashMap();
        map.put("ubigeo", ubigeo);
        
        return this.getDao().buscar(jpql, map);
    }
    
    public static UbigeoControlador getInstance() {
        return UbigeoControladorHolder.INSTANCE;
    }
    
    private static class UbigeoControladorHolder {

        private static final UbigeoControlador INSTANCE = new UbigeoControlador();
    }
}
