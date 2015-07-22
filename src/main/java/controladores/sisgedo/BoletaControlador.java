/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores.sisgedo;

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
        String sql = 
                "SELECT "
                + "boleta.* "
                + "FROM "
                + "SPa_boleta boleta INNER JOIN SPa_motivo motivo ON boleta.idmotivo = motivo.idmotivo AND "
                + "SPa_usuario usuario INNER JOIN boleta ON boleta.login = usuario.login "
                + "WHERE usuario.dni = :empleado";
        Map<String,Object> parametros = new HashMap<>();
        parametros.put("empleado", empleado.getNroDocumento());
        List<Boleta> permisos = this.getDao().getEntityManager().createNativeQuery(sql, Boleta.class)
                .setParameter("empleado", empleado.getNroDocumento())
                .getResultList();
        return permisos;
    }
    public List<Boleta> permisoXFechaXEmpleado(Empleado empleado, Date fecha){
        String sql = 
                "SELECT "
                + "boleta.* "
                + "FROM "
                + "SPa_boleta boleta INNER JOIN SPa_motivo motivo ON boleta.idmotivo = motivo.idmotivo AND "
                + "SPa_usuario usuario INNER JOIN boleta ON boleta.login = usuario.login "
                + "WHERE usuario.dni = :empleado AND :fecha BETWEEN boleta.horasal AND boleta.horaret";
        Map<String,Object> parametros = new HashMap<>();
        parametros.put("empleado", empleado.getNroDocumento());
        List<Boleta> permisos = this.getDao().getEntityManager().createNativeQuery(sql, Boleta.class)
                .setParameter("empleado", empleado.getNroDocumento())
                .setParameter("fecha", fecha)
                .getResultList();
        return permisos;
    }
    
    private static class BoletaControladorHolder {

        private static final BoletaControlador INSTANCE = new BoletaControlador();
    }
}
