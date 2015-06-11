/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas.modelos;

import entidades.DetalleJornada;
import entidades.Jornada;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.TableModel;
import org.jdesktop.swingx.treetable.AbstractTreeTableModel;

/**
 *
 * @author RyuujiMD
 */
public class MTRJornada extends AbstractTreeTableModel {

    private final List<Jornada> jornadas;
    private final String[] COLUMNAS = {"Hora de entrada", "Hora de Salida"};
    private final DateFormat dfHora = new SimpleDateFormat("HH:mm");

    public MTRJornada(List<Jornada> jornadas) {
        super(new Object());
        this.jornadas = jornadas;
    }

    @Override
    public int getColumnCount() {
        return COLUMNAS.length;
    }

    @Override
    public String getColumnName(int column) {
        return COLUMNAS[column]; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getValueAt(Object nodo, int columna) {
        if (nodo instanceof Jornada) {
            Jornada jornada = (Jornada) nodo;
            switch (columna) {
                case 0:
                    return jornada.getNombre();
            }
        } else if (nodo instanceof DetalleJornada) {
            DetalleJornada detalle = (DetalleJornada) nodo;
            switch (columna) {
                case 0:
                    return dfHora.format(detalle.getEntrada());
                case 1:
                    return dfHora.format(detalle.getSalida());
            }
        }

        return null;
    }

    @Override
    public Object getChild(Object parent, int index) {
        if (parent instanceof Jornada) {
            Jornada jornada = (Jornada) parent;
            return jornada.getDetalleJornadaList().get(index);
        } else {
            return this.jornadas.get(index);
        }
    }

    @Override
    public int getChildCount(Object parent) {
        if (parent instanceof Jornada) {
            Jornada jornada = (Jornada) parent;
            return jornada.getDetalleJornadaList().size();
        } else {
            return this.jornadas.size();
        }

    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        if (parent instanceof Jornada && child instanceof DetalleJornada) {
            Jornada jornada = (Jornada) parent;
            DetalleJornada detalleJornada = (DetalleJornada) child;
            return jornada.getDetalleJornadaList().indexOf(detalleJornada);
        }else{
            return -1;
        }

    }

    @Override
    public boolean isLeaf(Object node) {
        return node instanceof DetalleJornada;
    }

    @Override
    public boolean isCellEditable(Object node, int column) {
        return false;
    }

}
