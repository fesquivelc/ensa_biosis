/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas.modelos;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 *
 * @author RyuujiMD
 * @param <TipoFiltro>
 */
public class MCFiltro<TipoFiltro> extends AbstractListModel implements ComboBoxModel{

    TipoFiltro seleccionado = TipoFiltro.POR_EMPLEADO;
    @Override
    public int getSize() {
        return 3;
    }

    @Override
    public TipoFiltro getElementAt(int index) {
        switch(index){
            case 0:
                return TipoFiltro.POR_EMPLEADO;
            case 1:
                return TipoFiltro.POR_OFICINA;
            case 2:
                return TipoFiltro.POR_GRUPO_HORARIO;
            default:
                return null;
        }
    }

    @Override
    public void setSelectedItem(Object anItem) {
        this.seleccionado = (TipoFiltro)anItem;
    }

    @Override
    public Object getSelectedItem() {
        return this.seleccionado;
    }
    public static enum TipoFiltro{
        POR_EMPLEADO,
        POR_OFICINA,
        POR_GRUPO_HORARIO,        
    }
}
