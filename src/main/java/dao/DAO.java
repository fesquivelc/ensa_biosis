/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;

/**
 *
 * @author RyuujiMD
 * @param <T>
 */
public interface DAO<T> {
    public EntityManager getEntityManager() throws Exception;
    public Connection getConexion() throws Exception;
    public Boolean crear(T objeto) throws Exception;
    public Boolean eliminar(T objeto) throws Exception;
    public Boolean actualizar(T objeto) throws Exception;
    public Boolean guardarLote(List<T> objeto) throws Exception;
    public List<T> buscar(String queryJPQL, Map<String, Object> parametros, int inicio, int tamanio) throws Exception;
    public int contar(String queryJPQL, Map<String, Object> parametros) throws Exception;
    public List<T> buscarTodos(Class<T> clase) throws Exception;
    public T buscarPorId(Object id, Class<T> clase) throws Exception;
    
}
