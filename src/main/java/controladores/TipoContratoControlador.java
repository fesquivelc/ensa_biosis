/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidades.escalafon.TipoContrato;

/**
 *
 * @author fesquivelc
 */
public class TipoContratoControlador extends Controlador<TipoContrato>{
    
    private TipoContratoControlador() {
        super(TipoContrato.class);
    }
    
    public static TipoContratoControlador getInstance() {
        return TipoContratoControladorHolder.INSTANCE;
    }
    
    private static class TipoContratoControladorHolder {

        private static final TipoContratoControlador INSTANCE = new TipoContratoControlador();
    }
}
