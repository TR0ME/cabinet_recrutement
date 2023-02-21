package eu.telecom_bretagne.cabinet_recrutement.data.dao;
// Generated Feb 2, 2023, 3:05:57 PM by Hibernate Tools 5.4.20.Final


import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import eu.telecom_bretagne.cabinet_recrutement.data.model.*;

/**
 * Home object for domain model class SecteurActivite.
 * @see eu.telecom_bretagne.cabinet_recrutement.data.model.SecteurActivite
 * @author Hibernate Tools
 */
@Stateless
public class SecteurActiviteDAO {

    private static final Logger logger = Logger.getLogger(SecteurActiviteDAO.class.getName());

    @PersistenceContext private EntityManager entityManager;
    
    public SecteurActivite persist(SecteurActivite transientInstance) {
        logger.log(Level.INFO, "persisting SecteurActivite instance");
        try {
            entityManager.persist(transientInstance);
            logger.log(Level.INFO, "persist successful");
        }
        catch (RuntimeException re) {
            logger.log(Level.SEVERE, "persist failed", re);
            throw re;
        }
        return transientInstance;
    }
    
    public void remove(SecteurActivite persistentInstance) {
        logger.log(Level.INFO, "removing SecteurActivite instance");
        try {
            if(!entityManager.contains(persistentInstance)){
                persistentInstance = entityManager.merge(persistentInstance);
            }
            entityManager.remove(persistentInstance);
            logger.log(Level.INFO, "remove successful");
        }
        catch (RuntimeException re) {
            logger.log(Level.SEVERE, "remove failed", re);
            throw re;
        }
    }
    
    public SecteurActivite merge(SecteurActivite detachedInstance) {
        logger.log(Level.INFO, "merging SecteurActivite instance");
        try {
            SecteurActivite result = entityManager.merge(detachedInstance);
            logger.log(Level.INFO, "merge successful");
            return result;
        }
        catch (RuntimeException re) {
            logger.log(Level.SEVERE, "merge failed", re);
            throw re;
        }
    }
    public SecteurActivite update(SecteurActivite secteurActivite) {
        if (secteurActivite != null) {
            entityManager.merge(secteurActivite);
        }
        return secteurActivite;
    }
    
    public SecteurActivite findById( int id) {
        logger.log(Level.INFO, "getting SecteurActivite instance with id: " + id);
        try {
            SecteurActivite instance = entityManager.find(SecteurActivite.class, id);
            logger.log(Level.INFO, "get successful");
            return instance;
        }
        catch (RuntimeException re) {
            logger.log(Level.SEVERE, "get failed", re);
            throw re;
        }
    }

    public List<SecteurActivite> findAll() {
        Query query = entityManager.createQuery("select secteurActivite from SecteurActivite secteurActivite order by secteurActivite.idSecteur");
        List l = query.getResultList();

        return (List<SecteurActivite>)l;
    }
}

