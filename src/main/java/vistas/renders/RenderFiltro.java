/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas.renders;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import vistas.modelos.MCFiltro.TipoFiltro;

/**
 *
 * @author RyuujiMD
 */
public class RenderFiltro extends DefaultListCellRenderer{

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if(value != null){
            if(value instanceof TipoFiltro){
                TipoFiltro filtro = (TipoFiltro)value;
                switch(filtro){
                    case TODO:
                        value = "TODO";
                        break;
                    case POR_EMPLEADO:
                        value = "EMPLEADO";
                        break;
                    case POR_OFICINA:
                        value = "OFICINA";
                        break;
                    case POR_GRUPO_HORARIO:
                        value = "GRUPO HORARIO";
                        break;
                }                
            }
        }
        return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus); //To change body of generated methods, choose Tools | Templates.
    }
    
}
