/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas.modelos;

import entidades.AsignacionHorario;
import com.personal.utiles.ModeloTabla;
import entidades.escalafon.Empleado;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 * @author fesquivelc
 */
public class MTAsignacionHorarioHR extends ModeloTabla<AsignacionHorario> {

    private final DateFormat dfFecha = new SimpleDateFormat("dd/MM/yyyy");

    public MTAsignacionHorarioHR(List<AsignacionHorario> datos) {
        super(datos);
        this.nombreColumnas = new String[]{"Empleado / Grupo", "Fecha de inicio", "Fecha de fin"};
    }

    @Override
    public Object getValorEn(int rowIndex, int columnIndex) {
        AsignacionHorario seleccion = this.datos.get(rowIndex);

        switch (columnIndex) {
            case 0:
                if (seleccion.isPorGrupo()) {
                    return "(GRUPO) "+seleccion.getGrupoHorario().getNombre();
                } else {
                    Empleado empleado = seleccion.getEmpleado();
                    if (empleado != null) {
                        return "(EMPLEADO) "+empleado.getPaterno() + " " + empleado.getMaterno() + " " + empleado.getNombre();
                    }

                }
            case 1:
                return dfFecha.format(seleccion.getFechaInicio());
            case 2:
                return dfFecha.format(seleccion.getFechaFin());

            default:
                return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

}
