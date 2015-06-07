/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas.modelos;

import entidades.Jornada;
import com.personal.utiles.ModeloTabla;
import entidades.DetalleJornada;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author fesquivelc
 */
public class MTDetalleJornada extends ModeloTabla<DetalleJornada> {

    private final DateFormat dtHora;

    public MTDetalleJornada(List<DetalleJornada> datos) {
        super(datos);
        dtHora = new SimpleDateFormat("HH:mm");
        this.nombreColumnas = new String[]{"Entrada","Tolerancia","Salida"};
    }
    private static final Logger LOG = Logger.getLogger(MTDetalleJornada.class.getName());

    @Override
    public Object getValorEn(int rowIndex, int columnIndex) {
        DetalleJornada detalle = this.datos.get(rowIndex);

        switch (columnIndex) {
            //ENTRADA
            case 0:
                return dtHora.format(detalle.getEntrada());                
            case 1:
                return dtHora.format(detalle.getEntradaTolerancia());
            //SALIDA            
            case 2:
                return dtHora.format(detalle.getSalida());
            default:
                return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class; //To change body of generated methods, choose Tools | Templates.
    }
}
