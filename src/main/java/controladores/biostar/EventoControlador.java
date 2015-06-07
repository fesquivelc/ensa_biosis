/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores.biostar;

import controladores.Controlador;
import dao.DAOBiostar;
import entidades.biostar.Evento;

/**
 *
 * @author RyuujiMD
 */
public class EventoControlador extends Controlador<Evento>{
    
    private EventoControlador() {
        super(Evento.class, new DAOBiostar<Evento>());
    }
    
    public static EventoControlador getInstance() {
        return EventoControladorHolder.INSTANCE;
    }
    
    private static class EventoControladorHolder {

        private static final EventoControlador INSTANCE = new EventoControlador();
    }
}
