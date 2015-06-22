/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas.renders;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author RyuujiMD
 */
public class RenderIndicadorAsistencia extends DefaultTableCellRenderer implements TableCellRenderer {

    private final JLabel label;

    private final Color REGULAR_COLOR = Color.decode("#a8e6cf");
    private final Color FALTA_COLOR = Color.decode("#ffaaa5");
    private final Color TARDANZA_COLOR = Color.decode("#ffebaf");
    private final Color PERMISO_COLOR = Color.decode("#ffd3b6");
    private final Color VACACION_COLOR = Color.decode("#e0cdff");
    private final Color FERIADO_COLOR = Color.decode("#b69f95");

    public RenderIndicadorAsistencia() {
        this.label = new JLabel();
        this.label.setOpaque(true);
        this.label.setFont(new Font(Font.SANS_SERIF, 0, 15));
        this.label.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.label.setForeground(Color.BLACK);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected) {
            this.label.setText(value != null ? value.toString() : "");
            this.label.setForeground(Color.WHITE);
            this.label.setBackground(Color.BLUE);
        } else {
            this.label.setForeground(Color.BLACK);
            Object elemento = table.getValueAt(row, 0);
            if (elemento != null) {
//            System.out.println(String.format("FILA: %s COLUMNA: %s ELEMENTO: %s", row, column, elemento));
                this.label.setText(value != null ? value.toString() : "");
                String valor = elemento.toString();
                if (valor.equals("R")) {
                    this.label.setBackground(REGULAR_COLOR);
                } else if (valor.equals("P")) {
                    this.label.setBackground(PERMISO_COLOR);
                } else if (valor.equals("F")) {
                    this.label.setBackground(FALTA_COLOR);
                } else if (valor.equals("E")) {
                    this.label.setBackground(FERIADO_COLOR);
                } else if (valor.equals("T")) {
                    this.label.setBackground(TARDANZA_COLOR);
                } else if (valor.equals("V")) {
                    this.label.setBackground(VACACION_COLOR);
                }

            } else {
                this.label.setText("");
                this.label.setForeground(Color.BLACK);
                this.label.setBackground(Color.BLACK);
            }
        }

        return this.label;

    }

}
