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
        
        List<Empleado> empleados = empc.buscarXPatron("40374022");
//        List<Empleado> empleados = empc.buscarXPatron("");
        System.out.println("INICIO: "+new Date());
        List<RptAsistenciaDetallado> asistenciaList = analisis.analisisDetallado(desde, hasta, empleados);
        System.out.println("FIN: "+new Date());
        ReporteUtil reporteador = new ReporteUtil();
        reporteador.setConn(empc.getDao().getConexion());
        
        File reporte = new File("reportes/reporte_registro_asistencia_caliente.jasper");
        Map<String,Object> parametros = new HashMap();
        reporteador.verReporte(asistenciaList, reporte, parametros, null);
        
        System.exit(0);
    }
    
}
