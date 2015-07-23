/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidades.TipoPermiso;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author fesquivelc
 */
public class TipoPermisoControlador extends Controlador<TipoPermiso>{

    public TipoPermisoControlador() {
        super(TipoPermiso.class);
    }
    
    public List<TipoPermiso> buscarXTipo(String tipo) {
        String jpql = "SELECT e FROM TipoPermiso e WHERE "
                + "UPPER(codigo) = UPPER(:tipo)";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("tipo", tipo);
        return this.getDao().buscar(jpql, mapa);
    }
}
