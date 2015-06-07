/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.DAO.em;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author RyuujiMD
 * @param <T>
 */
public class DAOBiostar<T> extends DAO {

    private final static String biostar_PU = "biostar-PU";
    private EntityManager emBiostar;

    @Override
    public EntityManager getEntityManager() {
        if (emBiostar == null) {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory(biostar_PU);
            emBiostar = emf.createEntityManager();
        }

        return emBiostar;
    }

}
