/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas.modelos;

import controladores.EmpleadoControlador;
import entidades.AsignacionHorario;
import com.personal.utiles.ModeloTabla;
import entidades.escalafon.Empleado;
import java.util.List;

/**
 *
 * @author fesquivelc
 */
public class MTAsignacion extends ModeloTabla<AsignacionHorario>{

    public MTAsignacion(List<AsignacionHorario> datos, String[] nombreColumnas) {
        super(datos, nombreColumnas);
    }

    @Override
    public Object getValorEn(int rowIndex, int columnIndex) {
        AsignacionHorario seleccion = this.datos.get(rowIndex);
        
        switch(columnIndex){
            case 0:
                return seleccion.getHorario().getCodigo();
            case 2:
                return seleccion.getHorario().getNombre();
            case 3:
                if(seleccion.isPorGrupo()){
                    return seleccion.getGrupoHorario().getNombre();                    
                }else{
                    Empleado empleado = seleccion.getEmpleado();
                    if(empleado != null){
                        return empleado.getPaterno() + " " + empleado.getMaterno() + " " + empleado.getNombre();
                    }
                    
                }
            default:
                return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch(columnIndex){
            case 2: case 3: case 4: case 5: case 6: case 7: case 8:
                return Boolean.class;
            default:
                return String.class;
        }
    }
    
    
    
}
