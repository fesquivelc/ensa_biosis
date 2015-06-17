/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utiles;

import entidades.reportes.RptAsistenciaDetallado;
import java.util.Comparator;
import java.util.Date;

/**
 *
 * @author RyuujiMD
 */
public class DetalleReporteAsistenciaComparator implements Comparator<RptAsistenciaDetallado> {

    @Override
    public int compare(RptAsistenciaDetallado primero, RptAsistenciaDetallado segundo) {
//        System.out.println("PRIMERO: "+primero.toString());
//        System.out.println("SEGUNDO: "+segundo.toString());
        int comparacionEmpleado = primero.getEmpleado().getNombreCompleto().compareTo(segundo.getEmpleado().getNombreCompleto());
        if (comparacionEmpleado == 0) {
            int comparacionFecha = primero.getFecha().compareTo(segundo.getFecha());
            if (comparacionFecha == 0) {
                int comparacionDetalle = primero.getDetalleJornada().getEntrada().compareTo(segundo.getDetalleJornada().getEntrada());
                if (comparacionDetalle == 0) {
                    Date comparacionI = primero.getTipoAsistencia().equals("P") ? primero.getPermiso().getHoraInicio() : primero.getDetalleJornada().getEntrada();
                    Date comparacionF = segundo.getTipoAsistencia().equals("P") ? segundo.getPermiso().getHoraInicio() : segundo.getDetalleJornada().getEntrada();

                    return comparacionI.compareTo(comparacionF);

                }
                return comparacionDetalle;
            } else {
                return comparacionFecha;
            }
        }
        return comparacionEmpleado;
    }

}
