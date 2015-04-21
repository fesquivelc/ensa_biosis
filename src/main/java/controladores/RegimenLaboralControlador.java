/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidades.escalafon.RegimenLaboral;

/**
 *
 * @author fesquivelc
 */
public class RegimenLaboralControlador extends Controlador<RegimenLaboral>{
    
    private RegimenLaboralControlador() {
        super(RegimenLaboral.class);
    }
    
    public static RegimenLaboralControlador getInstance() {
        return RegimenLaboralControladorHolder.INSTANCE;
    }
    
    private static class RegimenLaboralControladorHolder {

        private static final RegimenLaboralControlador INSTANCE = new RegimenLaboralControlador();
    }
}
