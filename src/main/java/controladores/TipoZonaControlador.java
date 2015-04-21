/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidades.escalafon.TipoZona;

/**
 *
 * @author fesquivelc
 */
public class TipoZonaControlador extends Controlador<TipoZona>{
    
    private TipoZonaControlador() {
        super(TipoZona.class);
    }
    
    public static TipoZonaControlador getInstance() {
        return TipoZonaControladorHolder.INSTANCE;
    }
    
    private static class TipoZonaControladorHolder {

        private static final TipoZonaControlador INSTANCE = new TipoZonaControlador();
    }
}
