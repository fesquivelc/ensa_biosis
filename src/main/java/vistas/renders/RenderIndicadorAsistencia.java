/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas.renders;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author RyuujiMD
 */
public class RenderIndicadorAsistencia extends JLabel implements TableCellRenderer {

    private final JLabel label;

    public RenderIndicadorAsistencia() {
        this.label = new JLabel();
        this.label.setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (value != null) {
            String valor = value.toString();
            if (valor.equals("R")) {
                this.label.setBackground(Color.GREEN);
                this.label.setForeground(Color.GREEN);
            } 
            else if (valor.equals("P")) {
                this.label.setBackground(Color.MAGENTA);
                this.label.setForeground(Color.MAGENTA);
            } 
            else if (valor.equals("F")) {
                this.label.setBackground(Color.RED);
                this.label.setForeground(Color.RED);
            } 
            else if (valor.equals("T")) {
                this.label.setBackground(Color.YELLOW);
                this.label.setForeground(Color.YELLOW);
            } 
            else {
                this.label.setForeground(Color.BLACK);
                this.label.setBackground(Color.BLACK);
            }

        } else {
            this.label.setText("");
            this.label.setForeground(Color.BLACK);
            this.label.setBackground(Color.BLACK);
        }

        return this.label;

    }

}
