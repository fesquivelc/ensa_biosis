/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidades.escalafon.SituacionTrabajador;

/**
 *
 * @author fesquivelc
 */
public class SituacionTrabajadorControlador extends Controlador<SituacionTrabajador>{
    
    private SituacionTrabajadorControlador() {
        super(SituacionTrabajador.class);
    }
    
    public static SituacionTrabajadorControlador getInstance() {
        return SituacionTrabajadorControladorHolder.INSTANCE;
    }
    
    private static class SituacionTrabajadorControladorHolder {

        private static final SituacionTrabajadorControlador INSTANCE = new SituacionTrabajadorControlador();
    }
}
