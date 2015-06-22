/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidades.CompraVacacion;
import entidades.Periodo;
import entidades.Vacacion;
import entidades.escalafon.Empleado;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author RyuujiMD
 */
public class CompraVacacionControlador extends Controlador<CompraVacacion> {

    private CompraVacacionControlador() {
        super(CompraVacacion.class);
    }

    public static CompraVacacionControlador getInstance() {
        return CompraVacacionControladorHolder.INSTANCE;
    }

    public int contarXFecha(Date fechaInicio, Date fechaFin) {
        String jpql = "SELECT COUNT(c) FROM CompraVacacion c WHERE c.fecha BETWEEN :fechaInicio AND :fechaFin";
        Map<String, Object> variables = new HashMap();
        variables.put("fechaInicio", fechaInicio);
        variables.put("fechaFin", fechaFin);
        return this.getDao().contar(jpql, variables);
    }

    public int contarXEmpleadoXFecha(Empleado empleado, Date fechaInicio, Date fechaFin) {
        String jpql = "SELECT COUNT(c) FROM CompraVacacion c WHERE c.empleado = :empleado AND c.fecha BETWEEN :fechaInicio AND :fechaFin";
        Map<String, Object> variables = new HashMap();
        variables.put("empleado", empleado);
        variables.put("fechaInicio", fechaInicio);
        variables.put("fechaFin", fechaFin);
        return this.getDao().contar(jpql, variables);
    }

    public List<CompraVacacion> buscarXFecha(Date fechaInicio, Date fechaFin, int pagina, int tamanio) {
        String jpql = "SELECT c FROM CompraVacacion c WHERE c.fecha BETWEEN :fechaInicio AND :fechaFin ORDER BY c.empleado.nombreCompleto";
        Map<String, Object> variables = new HashMap();
        variables.put("fechaInicio", fechaInicio);
        variables.put("fechaFin", fechaFin);
        return this.getDao().buscar(jpql, variables, pagina, tamanio);
    }

    public List<CompraVacacion> buscarXEmpleadoXFecha(Empleado empleado, Date fechaInicio, Date fechaFin, int pagina, int tamanio) {
        String jpql = "SELECT COUNT(c) FROM CompraVacacion c WHERE c.empleado = :empleado AND c.fecha BETWEEN :fechaInicio AND :fechaFin ORDER BY c.empleado.nombreCompleto";
        Map<String, Object> variables = new HashMap();
        variables.put("empleado", empleado);
        variables.put("fechaInicio", fechaInicio);
        variables.put("fechaFin", fechaFin);
        return this.getDao().buscar(jpql, variables, pagina, tamanio);
    }

    private static class CompraVacacionControladorHolder {

        private static final CompraVacacionControlador INSTANCE = new CompraVacacionControlador();
    }

    public List<CompraVacacion> buscarXEmpleadoXPeriodo(Empleado empleado, Periodo periodo) {
        String jpql = "SELECT c FROM CompraVacacion c WHERE c.empleado = :empleado AND c.periodo = :periodo";
        Map<String, Object> variables = new HashMap();
        variables.put("empleado", empleado);
        variables.put("periodo", periodo);

        return this.getDao().buscar(jpql, variables);
    }
}
