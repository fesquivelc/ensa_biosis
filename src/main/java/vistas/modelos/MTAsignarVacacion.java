/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas.modelos;

import entidades.Vacacion;
import com.personal.utiles.ModeloTabla;
import entidades.InterrupcionVacacion;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 * @author fesquivelc
 */
public class MTAsignarVacacion extends ModeloTabla<Vacacion>{
    private final DateFormat dfFecha;    

    public MTAsignarVacacion(List<Vacacion> datos) {
        super(datos);
        dfFecha = new SimpleDateFormat("dd.MM.yyyy");
        this.nombreColumnas = new String[]{"Código","Nro de documento","Fecha de inicio","Fecha de fin","Interrupcion","Reprogramacion"};
    }

    @Override
    public Object getValorEn(int rowIndex, int columnIndex) {
        Vacacion vacacion = this.datos.get(rowIndex);
        switch(columnIndex){
            case 0:
                return vacacion.getEmpleado().getFichaLaboral().getCodigoTrabajador();
            case 1:
                return vacacion.getEmpleado().getNombreCompleto();
            case 2:
                return dfFecha.format(vacacion.getFechaInicio());
            case 3:
                return dfFecha.format(vacacion.getFechaFin());
            case 4:
                InterrupcionVacacion interrupcion = vacacion.getInterrupcionVacacion();
                if(interrupcion != null){
                    return String.format("INTERRUPCIÓN DESDE %s HASTA %s", dfFecha.format(interrupcion.getFechaInicio()), dfFecha.format(interrupcion.getFechaFin()));
                }else{
                    return "NO";
                }
            case 5:
                Vacacion reprogramacion = vacacion.getVacacionReprogramacion();
                if(reprogramacion != null){
                    return String.format("REPROGRAMADO DESDE %s HACIA  %s", dfFecha.format(reprogramacion.getVacacionOrigen().getFechaInterrupcion()),dfFecha.format(reprogramacion.getFechaInicio()));
                }else{
                    return "NO";
                }
            default:
                return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return Object.class;
    }
    
    
    
    
    
}
