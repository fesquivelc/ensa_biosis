/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.personal.utiles.ParametrosUtil;
import com.personal.utiles.PropertiesUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import utiles.Encriptador;

/**
 *
 * @author gabriel
 * @param <T>
 */
public class DAOSISGEDO<T> extends DAOBiosis<T>{

    protected String PUSISGEDO = "sisgedo-PU";
    protected static EntityManager emSISGEDO;

    public DAOSISGEDO(Class<T> clase) {
        this.clase = clase;
    }

    public DAOSISGEDO() {
        this.clase = null;
    }

    @Override
    public EntityManager getEntityManager() {
        if (emSISGEDO == null) {
            Properties configuracion = PropertiesUtil.cargarProperties("config/sisgedo-config.properties");
            int tipoBD = Integer.parseInt(configuracion.getProperty("tipo"));

            String driver = ParametrosUtil.obtenerDriver(tipoBD);
            String url = configuracion.getProperty("url");
            String usuario = configuracion.getProperty("usuario");
            String password = configuracion.getProperty("password");

            Map<String, String> properties = new HashMap<>();
            properties.put("javax.persistence.jdbc.user", usuario);
            properties.put("javax.persistence.jdbc.password", Encriptador.decrypt(password));
            properties.put("javax.persistence.jdbc.driver", driver);
            properties.put("javax.persistence.jdbc.url", url);
//            properties.put("hibernate.show_sql", "true");
            properties.put("javax.persistence.schema-generation.database.action", "none");

//            try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory(this.PUSISGEDO, properties);
            emSISGEDO = emf.createEntityManager();
//            } catch (Exception e) {
//                System.out.println("EM: "+e.getCause().getMessage()+" "+e.getMessage());
//            }

        }
        return emSISGEDO;
    }
//
//    public Connection getConexion() {
//        Session sesion = (Session) getEntityManager().getDelegate();
//        SessionFactoryImpl sessionFactory = (SessionFactoryImpl) sesion.getSessionFactory();
//        Connection connection = null;
//        try {
//            connection = sessionFactory.getConnectionProvider().getConnection();
//            if (connection == null) {
//                LOG.error("NO SE PUDO OBTENER LA CONEXION");
//            }
//        } catch (SQLException e) {
//            LOG.error("ERROR " + e.getMessage());
//            em = null;
//        }
//        return connection;
//
//    }
//
//    public boolean guardar(T objeto) {
//        try {
//            getEntityManager().getTransaction().begin();
//            getEntityManager().persist(objeto);
//            getEntityManager().getTransaction().commit();
//            getEntityManager().clear();
//            return true;
//        } catch (Exception e) {
//            LOG.error("ERROR EN EL GUARDADO: " + e.getLocalizedMessage() + " " + e.getMessage());
//            em = null;
//            return false;
//        }
//
//    }
//
//    public boolean guardarLote(List<T> lote) {
//        try {
//            getEntityManager().getTransaction().begin();
//            for (T objeto : lote) {
//                getEntityManager().persist(objeto);
//            }
//            getEntityManager().getTransaction().commit();
//            return true;
//        } catch (Exception e) {
//            LOG.error("ERROR EN EL GUARDADO POR LOTE: " + e.getLocalizedMessage() + " " + e.getMessage());
//            em = null;
//            return false;
//        }
//    }
//
//    public boolean modificar(T objeto) {
//        try {
//            getEntityManager().getTransaction().begin();
//            getEntityManager().merge(objeto);
//            getEntityManager().getTransaction().commit();
//            return true;
//        } catch (Exception e) {
//            LOG.error("ERROR AL MODIFICAR: " + e.getLocalizedMessage() + " " + e.getMessage());
//            em = null;
//            return false;
//        }
//
//    }
//
//    public boolean eliminar(T objeto) {
//        try {
//            getEntityManager().getTransaction().begin();
//            getEntityManager().remove(objeto);
//            getEntityManager().getTransaction().commit();
//            return true;
//        } catch (Exception e) {
//            LOG.error("ERROR AL ELIMINAR: " + e.getLocalizedMessage() + " " + e.getMessage());
//            em = null;
//            return false;
//        }
//    }
//
//    public List<T> buscar(String queryJPQL) {
//        return this.buscar(queryJPQL, null, -1, -1);
//    }
//
//    public List<T> buscar(String queryJPQL, Map<String, Object> parametros) {
//        return this.buscar(queryJPQL, parametros, -1, -1);
//    }
//
//    public List<T> buscar(String queryJPQL, Map<String, Object> parametros, int inicio, int tamanio) {
//        try {
//            Query query = getEntityManager().createQuery(queryJPQL);
//
//            if (parametros != null) {
//                for (Map.Entry<String, Object> entry : parametros.entrySet()) {
//                    query.setParameter(entry.getKey(), entry.getValue());
//                }
//            }
//
//            if (inicio != -1) {
//                query.setFirstResult(inicio);
//            }
//
//            if (tamanio != -1) {
//                query.setMaxResults(tamanio);
//            }
//
//            List<T> lista = query.getResultList();
//
//            return lista;
//        } catch (Exception e) {
//            LOG.error("ERROR AL BUSCAR: " + e.getLocalizedMessage() + " " + e.getMessage());
//            em = null;
//            return null;
//        }
//
//    }
//
//    public int contar(String queryJPQL, Map<String, Object> parametros) {
//        try {
//            Query query = getEntityManager().createQuery(queryJPQL);
//
//            if (parametros != null) {
//                for (Map.Entry<String, Object> entry : parametros.entrySet()) {
//                    query.setParameter(entry.getKey(), entry.getValue());
//                }
//            }
//
//            Long conteo = (Long) query.getSingleResult();
//
//            return conteo.intValue();
//        } catch (Exception e) {
//            LOG.error("ERROR AL CONTAR: " + e.getLocalizedMessage() + " " + e.getMessage());
//            em = null;
//            return 0;
//        }
//
//    }
//
//    public List<T> buscarTodos() {
//        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
//        cq.select(cq.from(clase));
//        return getEntityManager().createQuery(cq).getResultList();
//    }
//
//    public int contar() {
//        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
//        javax.persistence.criteria.Root<T> rt = cq.from(clase);
//        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
//        javax.persistence.Query q = getEntityManager().createQuery(cq);
//        return ((Long) q.getSingleResult()).intValue();
//    }
//
//    public T buscarPorId(Object id) {
//        return getEntityManager().find(clase, id);
//    }
//
//    private void rollback() {
//        getEntityManager().getTransaction().begin();
//        getEntityManager().getTransaction().rollback();
//    }
}
