/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas.modelos;

import entidades.Marcacion;
import com.personal.utiles.ModeloTabla;
import entidades.escalafon.Empleado;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 * @author fesquivelc
 */
public class MTMarcacionRA extends ModeloTabla<Marcacion> {

    private final DateFormat dtFecha;
    private final DateFormat dtHora;
    
    public MTMarcacionRA(List<Marcacion> datos) {
        super(datos);
        this.nombreColumnas = new String[]{"Fecha", "Hora", "Equipo"};
        dtFecha = new SimpleDateFormat("dd.MM.yyyy");
        dtHora = new SimpleDateFormat("HH:mm:ss");
    }

    @Override
    public Object getValorEn(int rowIndex, int columnIndex) {
        Marcacion marcacion = this.datos.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return dtFecha.format(marcacion.getFechaHora());
            case 1:
                return dtHora.format(marcacion.getFechaHora());
            case 2:
                return "SEDE PRINCIPAL";//marcacion.getEquipo();
            default:
                return null;
        }
    }

}
