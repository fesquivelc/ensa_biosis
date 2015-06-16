/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import algoritmo.AnalisisAsistenciaCaliente;
import com.personal.utiles.FechaUtil;
import com.personal.utiles.ReporteUtil;
import controladores.EmpleadoControlador;
import entidades.escalafon.Empleado;
import entidades.reportes.RptAsistenciaDetallado;
import java.io.File;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author RyuujiMD
 */
public class PruebaReporteCaliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        EmpleadoControlador empc = new EmpleadoControlador();
        AnalisisAsistenciaCaliente analisis = new AnalisisAsistenciaCaliente();
        Calendar cal = Calendar.getInstance();
        cal.set(2015, 4, 1);
        Date desde = FechaUtil.soloFecha(cal.getTime());
        cal.set(2015,4,31,23,59,59);
        Date hasta = cal.getTime();
        
        List<Empleado> empleados = empc.buscarXPatron("27916207");
        System.out.println("INICIO: "+new Date());
        List<RptAsistenciaDetallado> asistenciaList = analisis.analisisDetallado(desde, hasta, empleados);
        System.out.println("FIN: "+new Date());
        ReporteUtil reporteador = new ReporteUtil();
        reporteador.setConn(empc.getDao().getConexion());
        
        List<RptAsistenciaDetallado> permisoList = filtrar(asistenciaList, 'P');
        
//        File reporte = new File("reportes/reporte_registro_asistencia_caliente.jasper");
//        Map<String,Object> parametros = new HashMap();
//        reporteador.verReporte(asistenciaList, reporte, parametros, null);
        
        File reporte2 = new File("reportes/ensa_sap_reporte_permiso.jasper");
        Map<String,Object> parametros2 = new HashMap();
        reporteador.verReporte(permisoList, reporte2, parametros2, null);
        
        System.exit(0);
    }

    private static List<RptAsistenciaDetallado> filtrar(List<RptAsistenciaDetallado> asistenciaList, char c) {
        List<RptAsistenciaDetallado> listado = new ArrayList<>();
        for(RptAsistenciaDetallado registro : asistenciaList){
            if(registro.getTipoAsistencia().charAt(0) == c){
                listado.add(registro);
            }
        }
        return listado;
    }
    
}
