/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores.sisgedo;

import com.personal.utiles.FechaUtil;
import controladores.Controlador;
import dao.DAOSISGEDO;
import entidades.escalafon.Empleado;
import entidades.sisgedo.Boleta;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author RyuujiMD
 */
public class BoletaControlador extends Controlador<Boleta>{
    
    private BoletaControlador() {
        super(Boleta.class, new DAOSISGEDO<Boleta>());
    }
    
    public static BoletaControlador getInstance() {
        return BoletaControladorHolder.INSTANCE;
    }    
    
    public List<Boleta> permisoXEmpleado(Empleado empleado){
        System.out.println("DNI: "+ empleado.getNroDocumento());
        String sql = 
                "SELECT "
                + "boleta.* "
                + "FROM "
                + "SPa_motivo motivo INNER JOIN SPa_boleta boleta ON boleta.idmotivo = motivo.idmotivo "
                + "INNER JOIN usuario us ON boleta.login = us.login "
                + "WHERE us.dni = :empleado "
                + "AND boleta.estado= 'C'";
        Map<String,Object> parametros = new HashMap<>();
        parametros.put("empleado", empleado.getNroDocumento());
        List<Boleta> permisos = this.getDao().getEntityManager().createNativeQuery(sql, Boleta.class)
                .setParameter("empleado", empleado.getNroDocumento())
                .getResultList();
        return permisos;
    }
    
    public Boleta permisoXFechaXEmpleado(Empleado empleado, Date fecha){
        String sql = 
                "SELECT "
                + "boleta.* "
                + "FROM "
                + "SPa_motivo motivo INNER JOIN SPa_boleta boleta ON boleta.idmotivo = motivo.idmotivo "
                + "INNER JOIN usuario us ON boleta.login = us.login "
                + "WHERE us.dni = :empleado "
                + "AND DATE_FORMAT(:fecha,'%Y-%m-%d') BETWEEN DATE_FORMAT(boleta.horasal,'%Y-%m-%d') AND DATE_FORMAT(boleta.horaret,'%Y-%m-%d') "
                + "AND boleta.estado= 'C' "
                + "AND datediff(horaret, horasal) > 0";
        Map<String,Object> parametros = new HashMap<>();
        parametros.put("empleado", empleado.getNroDocumento());
        List<Boleta> permisos = this.getDao().getEntityManager().createNativeQuery(sql, Boleta.class)
                .setParameter("empleado", empleado.getNroDocumento())
                .setParameter("fecha", fecha)
                .getResultList();
        
        if(permisos.isEmpty()){
            System.out.println("LISTA X FECHA ES NULL");
            return null;
        }else{
            return permisos.get(0);
        }
    }
    
    public List<Boleta> permisoXHoraXFecha(Empleado empleado, Date fecha, Date horaInicio, Date horaFin){
        String sql = 
                "SELECT "
                + "boleta.* "
                + "FROM "
                + "SPa_motivo motivo INNER JOIN SPa_boleta boleta ON boleta.idmotivo = motivo.idmotivo "
                + "INNER JOIN usuario us ON boleta.login = us.login "
                + "WHERE us.dni = :empleado "
                + "AND DATE_FORMAT(boleta.horasal,'%H:%i:%s') BETWEEN :fechaInicio AND :fechaFin "
                + "AND DATE_FORMAT(boleta.fecapr,'%Y-%m-%d') = DATE_FORMAT(:fecha,'%Y-%m-%d')"
//                + "AND boleta.horasal BETWEEN :fechaInicio AND :fechaFin "
                + "AND boleta.estado= 'C' "
                + "AND datediff(boleta.horaret, boleta.horasal) = 0";
        Map<String,Object> parametros = new HashMap<>();
        parametros.put("empleado", empleado.getNroDocumento());
        List<Boleta> permisos = this.getDao().getEntityManager().createNativeQuery(sql, Boleta.class)
                .setParameter("empleado", empleado.getNroDocumento())
//                .setParameter("fechaInicio", FechaUtil.unirFechaHora(fecha, horaInicio))
//                .setParameter("fechaFin", FechaUtil.unirFechaHora(fecha, horaFin))
                .setParameter("fechaInicio",  horaInicio)
                .setParameter("fechaFin", horaFin)
                .setParameter("fecha", fecha)
                .getResultList();
        return permisos;
    }
    
    private static class BoletaControladorHolder {

        private static final BoletaControlador INSTANCE = new BoletaControlador();
    }
}
