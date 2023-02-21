package eu.telecom_bretagne.cabinet_recrutement.data.dao;
// Generated Feb 2, 2023, 3:05:57 PM by Hibernate Tools 5.4.20.Final


import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;
import eu.telecom_bretagne.cabinet_recrutement.data.model.*;

/**
 * Home object for domain model class MessageCandidat.
 * @see eu.telecom_bretagne.cabinet_recrutement.data.model.MessageCandidat
 * @author Hibernate Tools
 */
@Stateless
public class MessageCandidatDAO {

    private static final Logger logger = Logger.getLogger(MessageCandidatDAO.class.getName());

    @PersistenceContext private EntityManager entityManager;
    
    public MessageCandidat persist(MessageCandidat transientInstance) {
        logger.log(Level.INFO, "persisting MessageCandidat instance");
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
    
    public void remove(MessageCandidat persistentInstance) {
        logger.log(Level.INFO, "removing MessageCandidat instance");
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
    
    public MessageCandidat merge(MessageCandidat detachedInstance) {
        logger.log(Level.INFO, "merging MessageCandidat instance");
        try {
            MessageCandidat result = entityManager.merge(detachedInstance);
            logger.log(Level.INFO, "merge successful");
            return result;
        }
        catch (RuntimeException re) {
            logger.log(Level.SEVERE, "merge failed", re);
            throw re;
        }
    }

    public MessageCandidat update(MessageCandidat messageCandidat) {
        if (messageCandidat != null) {
            entityManager.merge(messageCandidat);
        }
        return messageCandidat;
    }
    
    public MessageCandidat findById( int id) {
        logger.log(Level.INFO, "getting MessageCandidat instance with id: " + id);
        try {
            MessageCandidat instance = entityManager.find(MessageCandidat.class, id);
            logger.log(Level.INFO, "get successful");
            return instance;
        }
        catch (RuntimeException re) {
            logger.log(Level.SEVERE, "get failed", re);
            throw re;
        }
    }

    public List<MessageCandidat> findAll()
    {
        Query query = entityManager.createQuery("select messageCandidat from MessageCandidat messageCandidat " +
                "order by messageCandidat.idMessageCandidat desc");
        List<MessageCandidat> l = query.getResultList();
        return l;
    }
}

