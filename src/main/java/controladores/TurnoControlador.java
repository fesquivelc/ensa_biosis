/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidades.Turno;

/**
 *
 * @author fesquivelc
 */
public class TurnoControlador extends Controlador<Turno>{
    
    private TurnoControlador() {
        super(Turno.class);
    }
    
    public static TurnoControlador getInstance() {
        return TurnoControladorHolder.INSTANCE;
    }
    
    private static class TurnoControladorHolder {

        private static final TurnoControlador INSTANCE = new TurnoControlador();
    }
}
