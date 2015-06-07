/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import com.personal.utiles.FechaUtil;
import entidades.Marcacion;
import entidades.escalafon.Empleado;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

/**
 *
 * @author fesquivelc
 */
public class MarcacionControlador extends Controlador<Marcacion> {

    private final Date horaFinal;

    public MarcacionControlador() {
        super(Marcacion.class);
        horaFinal = new Date(104399000);
    }

    public List<Marcacion> buscarXFecha(Empleado empleado, Date fechaInicio, Date fechaFin, int desde, int tamanio) {
        String jpql = "SELECT m FROM Marcacion m WHERE m.empleado = :dni AND m.fechaHora BETWEEN :fechaInicio AND :fechaFin "
                + "ORDER BY m.empleado.paterno,m.empleado.materno,m.empleado.nombre,m.fechaHora";
        LOG.error("DOCUMENTO: " + empleado);
        Map<String, Object> mapa = new HashMap<>();

        mapa.put("dni", empleado);
        mapa.put("fechaInicio", FechaUtil.soloFecha(fechaInicio));
        mapa.put("fechaFin", FechaUtil.unirFechaHora(fechaFin, horaFinal));
        List<Marcacion> marcaciones = this.getDao().buscar(jpql, mapa, desde, tamanio);
        return marcaciones;
    }

    public List<Marcacion> buscarXFecha(Date fechaInicio, Date fechaFin) {
        String jpql = "SELECT m FROM Marcacion m WHERE m.fechaHora BETWEEN :fechaInicio AND :fechaFin";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("fechaInicio", FechaUtil.soloFecha(fechaInicio));
        mapa.put("fechaFin", FechaUtil.unirFechaHora(fechaFin, horaFinal));
        return this.getDao().buscar(jpql, mapa);
    }

    public List<Marcacion> buscarXFecha(Date fecha) {
        String jpql = "SELECT m FROM Marcacion m WHERE m.fechaHora BETWEEN :fechaInicio AND :fechaFin";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("fechaInicio", FechaUtil.soloFecha(fecha));
        mapa.put("fechaFin", FechaUtil.unirFechaHora(fecha, horaFinal));
        return this.getDao().buscar(jpql, mapa);
    }

    public List<Marcacion> buscarXFecha(Empleado empleado, Date fecha) {
        String jpql = "SELECT m FROM Marcacion m WHERE m.empleado = :dni AND m.fechaHora BETWEEN :fechaInicio AND :fechaFin ORDER BY m.fechaHora ASC";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("fechaInicio", FechaUtil.soloFecha(fecha));
        mapa.put("fechaFin", FechaUtil.unirFechaHora(fecha, horaFinal));
        mapa.put("dni", empleado);
//        mapa.put("ceros", this.ceros(dni));
        return this.getDao().buscar(jpql, mapa);
    }

    public List<Marcacion> buscarXFecha(Date fechaInicio, Date fechaFin, int desde, int tamanio) {
        String jpql = "SELECT m FROM Marcacion m WHERE m.fechaHora BETWEEN :fechaInicio AND :fechaFin "
                + "ORDER BY m.empleado.paterno,m.empleado.materno,m.empleado.nombre,m.fechaHora";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("fechaInicio", FechaUtil.soloFecha(fechaInicio));
        mapa.put("fechaFin", FechaUtil.unirFechaHora(fechaFin, horaFinal));
        List<Marcacion> lista = this.getDao().buscar(jpql, mapa, desde, tamanio);
        return lista;
    }

    public List<Marcacion> buscarXFechaXHora(Date fechaInicio, Date horaInicio, Date horaFin, int desde, int tamanio) {
        String jpql = "SELECT m FROM Marcacion m WHERE m.fechaHora BETWEEN :fechaInicio AND :fechaFin "
                + "ORDER BY m.empleado.paterno,m.empleado.materno,m.empleado.nombre,m.fechaHora";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("fechaInicio", FechaUtil.unirFechaHora(fechaInicio, horaInicio));
        mapa.put("fechaFin", FechaUtil.unirFechaHora(fechaInicio, horaFin));
        List<Marcacion> lista = this.getDao().buscar(jpql, mapa, desde, tamanio);
        return lista;
    }

    private static final Logger LOG = Logger.getLogger(MarcacionControlador.class.getName());

    public int totalXEmpleadoXFecha(String dni, Date fechaInicio, Date fechaFin) {
        String jpql = "SELECT COUNT(m.id) FROM Marcacion m WHERE m.empleado = :dni AND m.fechaHora BETWEEN :fechaInicio AND :fechaFin";
        Long cont = (Long) this.getDao().getEntityManager().createQuery(jpql)
                .setParameter("dni", dni)
                .setParameter("fechaInicio", FechaUtil.soloFecha(fechaInicio))
                .setParameter("fechaFin", FechaUtil.unirFechaHora(fechaFin, horaFinal)).getSingleResult();
        int conteo = cont.intValue();
        return conteo;
    }

    public int totalXFecha(Date fechaInicio, Date fechaFin) {
        String jpql = "SELECT COUNT(m.id) FROM Marcacion m WHERE m.fechaHora BETWEEN :fechaInicio AND :fechaFin ";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("fechaInicio", FechaUtil.soloFecha(fechaInicio));
        mapa.put("fechaFin", FechaUtil.unirFechaHora(fechaFin, horaFinal));
        return this.getDao().contar(jpql, mapa);
    }

    public Marcacion buscarXFechaXhora(Empleado empleado, Date fecha, Date horaI, Date horaF) {
        String jpql = "SELECT m FROM Marcacion m WHERE "
                + "m.empleado = :dni "
                + "AND m.fechaHora BETWEEN :fechaInicio AND :fechaFin "
                + "ORDER BY m.fechaHora ASC";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("dni", empleado);
        mapa.put("fechaInicio", FechaUtil.unirFechaHora(fecha, horaI));
        mapa.put("fechaFin", FechaUtil.unirFechaHora(fecha, horaF));
        List<Marcacion> marcaciones = this.getDao().buscar(jpql, mapa, -1, 1);

        if (marcaciones.isEmpty()) {
            return null;
        } else {
            return marcaciones.get(0);
        }
    }
    
    public Marcacion buscarXFechaXhora(Empleado empleado, Date fechaInicio, Date fechaFin, Date horaInicio, Date horaFin) {
        String jpql = "SELECT m FROM Marcacion m WHERE "
                + "m.empleado = :dni "
                + "AND m.fechaHora BETWEEN :fechaInicio AND :fechaFin "
                + "ORDER BY m.fechaHora ASC";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("dni", empleado);
        mapa.put("fechaInicio", FechaUtil.unirFechaHora(fechaInicio, horaInicio));
        mapa.put("fechaFin", FechaUtil.unirFechaHora(fechaFin, horaFin));
        List<Marcacion> marcaciones = this.getDao().buscar(jpql, mapa, -1, 1);

        if (marcaciones.isEmpty()) {
            return null;
        } else {
            return marcaciones.get(0);
        }
    }
//
//    public List<Object[]> buscarEmpleadosXEvento(int evento, boolean dentro) {
//        String sql = "SELECT u.sUserID,CONVERT(varchar,u.sUserName),CONVERT (varchar,dep.sName) FROM TB_USER u LEFT JOIN TB_USER_DEPT dep on u.nDepartmentIdn = dep.nDepartmentIdn WHERE u.sUserID";
//        if (dentro) {
//            sql += " IN ";
//        } else {
//            sql += " NOT IN ";
//        }
//        sql += "(SELECT "
//                + "TB_USER.sUserID "
//                + "FROM TB_EVENT_LOG "
//                + "INNER JOIN TB_USER ON TB_EVENT_LOG.nUserID = TB_USER.nUserIdn "
//                + "WHERE TB_EVENT_LOG.nEventIdn = :evento)";
//        sql += " ORDER BY u.sUserID,u.sUserName";
//        System.out.println("SQL: " + sql);
//        List<Object[]> lista = this.getDao().getEntityManager().createNativeQuery(sql).setParameter("evento", evento).getResultList();
//        return lista;
//    }

    public List<Marcacion> buscarXFechaXHora(Empleado empleado, Date fechaInicio, Date horaInicio, Date horaFin, int desde, int tamanio) {
        String jpql = "SELECT m FROM Marcacion m WHERE "
                + "m.empleado = :dni "
                + "AND m.fechaHora BETWEEN :fechaInicio AND :fechaFin "
                + "ORDER BY m.fechaHora ASC";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("dni", empleado);
        mapa.put("fechaInicio", FechaUtil.unirFechaHora(fechaInicio, horaInicio));
        mapa.put("fechaFin", FechaUtil.unirFechaHora(fechaInicio, horaFin));
        List<Marcacion> marcaciones = this.getDao().buscar(jpql, mapa, desde, tamanio);
        return marcaciones;
    }

    public List<Marcacion> buscarXFechaXHora(List<Empleado> empleados, Date fechaInicio, Date horaInicio, Date horaFin, int desde, int tamanio) {
        String jpql = "SELECT m FROM Marcacion m WHERE "
                + "m.empleado IN :dni "
                + "AND m.fechaHora BETWEEN :fechaInicio AND :fechaFin "
                + "ORDER BY m.fechaHora ASC";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("dni", empleados);
        mapa.put("fechaInicio", FechaUtil.unirFechaHora(fechaInicio, horaInicio));
        mapa.put("fechaFin", FechaUtil.unirFechaHora(fechaInicio, horaFin));
        List<Marcacion> marcaciones = this.getDao().buscar(jpql, mapa, desde, tamanio);
        return marcaciones;
    }

    public List<Marcacion> buscarXFecha(List<Empleado> empleados, Date fechaInicio, Date fechaFin, int desde, int tamanio) {
        String jpql = "SELECT m FROM Marcacion m WHERE m.empleado IN :dni AND m.fechaHora BETWEEN :fechaInicio AND :fechaFin "
                + "ORDER BY m.empleado.paterno,m.empleado.materno,m.empleado.nombre,m.fechaHora";
        Map<String, Object> mapa = new HashMap<>();

        mapa.put("dni", empleados);
        mapa.put("fechaInicio", FechaUtil.soloFecha(fechaInicio));
        Date fechaFinUnida = FechaUtil.unirFechaHora(fechaFin, horaFinal);
        mapa.put("fechaFin", fechaFinUnida);
        LOG.info(String.format("FECHA INICIO: %s - FECHA FIN: %s", fechaInicio, fechaFinUnida));
        List<Marcacion> marcaciones = this.getDao().buscar(jpql, mapa, desde, tamanio);
        return marcaciones;
    }

    public int totalXFechaXHora(Date fechaInicio, Date horaInicio, Date horaFin) {
        String jpql = "SELECT COUNT(m.id) FROM Marcacion m WHERE m.fechaHora BETWEEN :fechaInicio AND :fechaFin ";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("fechaInicio", FechaUtil.unirFechaHora(fechaInicio, horaInicio));
        mapa.put("fechaFin", FechaUtil.unirFechaHora(fechaInicio, horaFin));
        return this.getDao().contar(jpql, mapa);
    }

    public int totalXFecha(List<Empleado> empleados, Date fechaInicio, Date fechaFin) {
        String jpql;
        jpql = "SELECT COUNT(m.id) FROM Marcacion m WHERE m.empleado IN :dni AND m.fechaHora BETWEEN :fechaInicio AND :fechaFin ";
        Map<String, Object> mapa = new HashMap<>();

        mapa.put("dni", empleados);
        mapa.put("fechaInicio", FechaUtil.soloFecha(fechaInicio));
        mapa.put("fechaFin", FechaUtil.unirFechaHora(fechaFin, horaFinal));
        return this.getDao().contar(jpql, mapa);
    }

    public int totalXFechaXHora(List<Empleado> empleados, Date fechaInicio, Date horaInicio, Date horaFin) {
        String jpql = "SELECT COUNT(m.id) FROM Marcacion m WHERE "
                + "m.empleado IN :dni "
                + "AND m.fechaHora BETWEEN :fechaInicio AND :fechaFin ";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("dni", empleados);
        mapa.put("fechaInicio", FechaUtil.unirFechaHora(fechaInicio, horaInicio));
        mapa.put("fechaFin", FechaUtil.unirFechaHora(fechaInicio, horaFin));
        return this.getDao().contar(jpql, mapa);
    }

}
