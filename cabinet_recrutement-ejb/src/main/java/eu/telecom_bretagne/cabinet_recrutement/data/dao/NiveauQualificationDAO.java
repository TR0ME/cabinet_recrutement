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
 * Home object for domain model class NiveauQualification.
 * @see eu.telecom_bretagne.cabinet_recrutement.data.model.NiveauQualification
 * @author Hibernate Tools
 */
@Stateless
public class NiveauQualificationDAO {

    private static final Logger logger = Logger.getLogger(NiveauQualificationDAO.class.getName());

    @PersistenceContext private EntityManager entityManager;
    
    public NiveauQualification persist(NiveauQualification transientInstance) {
        logger.log(Level.INFO, "persisting NiveauQualification instance");
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
    
    public void remove(NiveauQualification persistentInstance) {
        logger.log(Level.INFO, "removing NiveauQualification instance");
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



    public NiveauQualification update(NiveauQualification niveauQualification) {
        if (niveauQualification != null) {
            entityManager.merge(niveauQualification);
        }
        return niveauQualification;
    }
    
    public NiveauQualification merge(NiveauQualification detachedInstance) {
        logger.log(Level.INFO, "merging NiveauQualification instance");
        try {
            NiveauQualification result = entityManager.merge(detachedInstance);
            logger.log(Level.INFO, "merge successful");
            return result;
        }
        catch (RuntimeException re) {
            logger.log(Level.SEVERE, "merge failed", re);
            throw re;
        }
    }
    
    public NiveauQualification findById( int id) {
        logger.log(Level.INFO, "getting NiveauQualification instance with id: " + id);
        try {
            NiveauQualification instance = entityManager.find(NiveauQualification.class, id);
            logger.log(Level.INFO, "get successful");
            return instance;
        }
        catch (RuntimeException re) {
            logger.log(Level.SEVERE, "get failed", re);
            throw re;
        }
    }


    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List<NiveauQualification> findAll()
    {
        Query query = entityManager.createQuery("select niveauQualification from NiveauQualification niveauQualification order by niveauQualification.idQualification");
        List l = query.getResultList();

        return (List<NiveauQualification>)l;
    }

}

