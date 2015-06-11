/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas.renders;

import entidades.escalafon.Departamento;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;

/**
 *
 * @author RyuujiMD
 */
public class RenderArea extends DefaultTreeCellRenderer implements TreeCellRenderer {

    private final JLabel label;

    public RenderArea() {
        this.label = new JLabel();
        this.label.setFont(new Font(Font.SANS_SERIF, 1, 15));        
        this.label.setOpaque(true);
    }

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        Object elemento = ((DefaultMutableTreeNode) value).getUserObject();
        
        ImageIcon icono = null;
        String iconoRuta = "";
        String titulo = "";
        if (elemento != null) {
            
            if (elemento instanceof Departamento) {
                Departamento departamento = (Departamento) elemento;
                iconoRuta = departamento.isSede() ? "iconos/icon_sede.png" : "iconos/icon_area.png";
                titulo = departamento.getNombre();
            } else{
                titulo = elemento.toString();
                iconoRuta = "iconos/icon_organigrama.png";
            }
                
            File iconoFichero = new File(iconoRuta);
            if(iconoFichero.exists()){
                icono = new ImageIcon(iconoFichero.getAbsolutePath());
            }
            if(hasFocus){
                this.label.setBackground(new Color(219, 237, 255));                
                this.label.setForeground(Color.BLACK);
            }else{
                this.label.setBackground(Color.WHITE);
                this.label.setForeground(Color.BLACK);
            }
            this.label.setIcon(icono);
            this.label.setText(titulo);
        }
        return this.label;
    }

}
