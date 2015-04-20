/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas.modelos;

import entidades.Horario;
import com.personal.utiles.ModeloTabla;
import java.util.List;

/**
 *
 * @author fesquivelc
 */
public class MTHorario extends ModeloTabla<Horario>{

    public MTHorario(List<Horario> datos, String[] nombreColumnas) {
        super(datos, nombreColumnas);
    }

    @Override
    public Object getValorEn(int rowIndex, int columnIndex) {
        Horario horario = this.datos.get(rowIndex);
        
        switch(columnIndex){
            case 0:
                return horario.getCodigo();
            case 1:
                return horario.getNombre();
            case 2:
                return mostrarTipo(horario.getTipo());                            
            default:
                return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    private String mostrarTipo(char tipo) {
        switch(tipo){
            case 'A':
                return "ADMINISTRATIVO";
            case 'T':
                return "TÉCNICO";
            default:
                return "";
        }
    }
    
    
    
}
