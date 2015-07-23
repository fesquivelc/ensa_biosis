/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas.modelos;

import com.personal.utiles.ModeloTabla;
import entidades.reportes.RptAsistenciaDetallado;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 * @author RyuujiMD
 */
public class MTDetalleRegistroAsistencia extends ModeloTabla<RptAsistenciaDetallado> {

    private final DateFormat dfFecha = new SimpleDateFormat("dd.MM.yyyy");
    private final DateFormat dfHora = new SimpleDateFormat("HH:mm:ss");

    public MTDetalleRegistroAsistencia(List<RptAsistenciaDetallado> datos) {
        super(datos);
        this.nombreColumnas = new String[]{"IND", "NRO DOCUMENTO", "EMPLEADO", "ÁREA", "FECHA", "TIPO", "MOTIVO", "H. REGULAR", "H. ENTRADA", "TARDANZA (min)", "H. SALIDA", "EXTRA (min)"};
    }

    @Override
    public Object getValorEn(int rowIndex, int columnIndex) {
        RptAsistenciaDetallado detalle = this.datos.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return detalle.getTipoAsistencia();
            case 1:
                return detalle.getEmpleado().getNroDocumento();
            case 2:
                return detalle.getEmpleado().getNombreCompleto();
            case 3:
                if (detalle.getArea() == null) {
                    return null;
                } else {
                    return detalle.getArea().getNombre();
                }

            case 4:
                return dfFecha.format(detalle.getFecha());
            case 5:
                if (detalle.getTipoAsistencia().equals("P")) {
                    if (detalle.getPermiso().getTipoPermiso().getCodigo().equals("SUS")) {
                        return this.resultado("S");
                    } else {
                        return this.resultado(detalle.getTipoAsistencia());
                    }
                } else {
                    return this.resultado(detalle.getTipoAsistencia());
                }

            case 6:
                if (detalle.getTipoAsistencia().equals("P")) {
                    return detalle.getPermiso().getTipoPermiso().getCodigo() + " - " + detalle.getPermiso().getMotivo();
                } else if (detalle.getTipoAsistencia().equals("E")) {
                    return detalle.getFeriado().getNombre();
                } else {
                    return null;
                }
            case 7:
                if (detalle.getDetalleJornada() != null) {
                    return String.format("%s - %s", dfHora.format(detalle.getDetalleJornada().getEntrada()), dfHora.format(detalle.getDetalleJornada().getSalida()));
                } else {
                    return null;
                }

            case 8:
                return detalle.getInicio() == null ? null : dfHora.format(detalle.getInicio());
            case 9:
                return detalle.getMinutosTardanza();
            case 10:
                return detalle.getFin() == null ? null : dfHora.format(detalle.getFin());
            case 11:
                return detalle.getMinutosExtra();
            default:
                return "";

        }
    }

    public String evento(char ev) {
        switch (ev) {
            case 'E':
                return "ENTRADA";
            case 'S':
                return "SALIDA";
            default:
                return "";
        }
    }

    public String tipo(char t) {
        switch (t) {
            case 'P':
                return "PERMISO";
            case 'R':
                return "REFRIGERIO";
            case 'T':
                return "JORNADA";
            default:
                return "";
        }
    }

    public String resultado(String r) {
        switch (r.charAt(0)) {
            case 'V':
                return "VACACIÓN";
            case 'S':
                return "SISGEDO - SALIDA";
            case 'P':
                return "PERMISO BIOSIS";
            case 'E':
                return "FERIADO";
            case 'T':
                return "TARDANZA";
            case 'R':
                return "REGULAR";
            case 'F':
                return "FALTA INJ.";
            case 'O':
                return "OBSERVACIÓN";
            default:
                return "";
        }
    }

}
