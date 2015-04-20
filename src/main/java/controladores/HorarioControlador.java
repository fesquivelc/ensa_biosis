/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidades.Horario;
import entidades.Turno;
import java.util.ArrayList;

/**
 *
 * @author fesquivelc
 */
public class HorarioControlador extends Controlador<Horario>{

    public HorarioControlador() {
        super(Horario.class);
    }

    @Override
    public void prepararCrear() {
        super.prepararCrear(); //To change body of generated methods, choose Tools | Templates.
        this.getSeleccionado().setTurnoList(new ArrayList<Turno>());
    }
    
}
