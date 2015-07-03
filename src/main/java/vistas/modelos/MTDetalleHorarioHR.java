/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas.modelos;

import com.personal.utiles.ModeloTabla;
import entidades.DetalleJornada;
import entidades.Jornada;
import entidades.Turno;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 * @author fesquivelc
 */
public class MTDetalleHorarioHR extends ModeloTabla<Turno> {

    private final DateFormat dfFecha = new SimpleDateFormat("dd.MM.yyyy");
    private final DateFormat dfHora = new SimpleDateFormat("HH:mm");

    public MTDetalleHorarioHR(List<Turno> datos) {
        super(datos);
        this.nombreColumnas = new String[]{"Jornada", "Det. Jornada", "Fec. inicio","Fec. fin","L","M","M","J","V","S","D"};
    }

    @Override
    public Object getValorEn(int rowIndex, int columnIndex) {
        Turno seleccion = this.datos.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return seleccion.getJornada().getNombre();
            case 1:
                return this.getDetalleJornada(seleccion.getJornada());
            case 2:
                return seleccion.getFechaInicio() == null ? null : dfFecha.format(seleccion.getFechaInicio());
            case 3:
                return seleccion.getFechaFin() == null ? null : dfFecha.format(seleccion.getFechaFin());
            case 4:
                return seleccion.isLunes();
            case 5:
                return seleccion.isMartes();
            case 6:
                return seleccion.isMiercoles();
            case 7:
                return seleccion.isJueves();
            case 8:
                return seleccion.isViernes();
            case 9:
                return seleccion.isSabado();
            case 10:
                return seleccion.isDomingo();
            default:
                return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(columnIndex >= 4){
            return Boolean.class;
        }
        return String.class;
    }

    private String getDetalleJornada(Jornada jornada) {
        String concatenacion = "";
        List<DetalleJornada> detalles = jornada.getDetalleJornadaList();
        int tamanio = detalles.size();
        int conteo = 1;
        for(DetalleJornada dt : detalles){
            concatenacion += String.format("%s - %s", dfHora.format(dt.getEntrada()),dfHora.format(dt.getSalida()));
            if(conteo < tamanio){
                concatenacion += " / ";
            }
            conteo ++;
        }
        
        return concatenacion;
    }

}
