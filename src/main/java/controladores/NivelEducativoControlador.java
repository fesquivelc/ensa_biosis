/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidades.escalafon.NivelEducativo;

/**
 *
 * @author fesquivelc
 */
public class NivelEducativoControlador extends Controlador<NivelEducativo>{
    
    private NivelEducativoControlador() {
        super(NivelEducativo.class);
    }
    
    public static NivelEducativoControlador getInstance() {
        return NivelEducativoControladorHolder.INSTANCE;
    }
    
    private static class NivelEducativoControladorHolder {

        private static final NivelEducativoControlador INSTANCE = new NivelEducativoControlador();
    }
}
