/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas.modelos;

import com.personal.utiles.ModeloTabla;
import entidades.CompraVacacion;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 * @author RyuujiMD
 */
public class MTCompraVacacion extends ModeloTabla<CompraVacacion>{

    private final DateFormat dfFecha = new SimpleDateFormat("dd.MM.yyyy");
    
    public MTCompraVacacion(List<CompraVacacion> datos) {
        super(datos);
        String[] columnas = {"Periodo", "Empleado","Dias comprados","Fecha de registro"};
    }
    
    @Override
    public Object getValorEn(int rowIndex, int columnIndex) {
        CompraVacacion compra = datos.get(rowIndex);
        
        switch(columnIndex){
            case 0:
                return compra.getPeriodo().getAnio();
            case 1:
                return compra.getEmpleado().getNombreCompleto();
            case 2:
                return compra.getDiasCompra();
            case 3:
                return compra.getFecha() == null ? null : dfFecha.format(compra.getFecha());
            default:
                return null;
        }
    }
    
}
