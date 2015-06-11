/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas.renders;

import java.awt.Component;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 *
 * @author RyuujiMD
 */
public class RenderJornada extends DefaultTreeCellRenderer{

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        return super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus); //To change body of generated methods, choose Tools | Templates.
    }
    
}
