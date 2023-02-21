package eu.telecom_bretagne.cabinet_recrutement.data.dao;
// Generated Feb 2, 2023, 3:05:57 PM by Hibernate Tools 5.4.20.Final


import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;
import eu.telecom_bretagne.cabinet_recrutement.data.model.*;

/**
 * Home object for domain model class MessageOffreemploi.
 * @see eu.telecom_bretagne.cabinet_recrutement.data.model.MessageOffreemploi
 * @author Hibernate Tools
 */
@Stateless
public class MessageOffreemploiDAO {

    private static final Logger logger = Logger.getLogger(MessageOffreemploiDAO.class.getName());

    @PersistenceContext private EntityManager entityManager;
    
    public MessageOffreemploi persist(MessageOffreemploi transientInstance) {
        logger.log(Level.INFO, "persisting MessageOffreemploi instance");
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
    
    public void remove(MessageOffreemploi persistentInstance) {
        logger.log(Level.INFO, "removing MessageOffreemploi instance");
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
    
    public MessageOffreemploi merge(MessageOffreemploi detachedInstance) {
        logger.log(Level.INFO, "merging MessageOffreemploi instance");
        try {
            MessageOffreemploi result = entityManager.merge(detachedInstance);
            logger.log(Level.INFO, "merge successful");
            return result;
        }
        catch (RuntimeException re) {
            logger.log(Level.SEVERE, "merge failed", re);
            throw re;
        }
    }
    public MessageOffreemploi update(MessageOffreemploi messageOffreemploi) {
        if (messageOffreemploi != null) {
            entityManager.merge(messageOffreemploi);
        }
        return messageOffreemploi;
    }
    
    public MessageOffreemploi findById( int id) {
        logger.log(Level.INFO, "getting MessageOffreemploi instance with id: " + id);
        try {
            MessageOffreemploi instance = entityManager.find(MessageOffreemploi.class, id);
            logger.log(Level.INFO, "get successful");
            return instance;
        }
        catch (RuntimeException re) {
            logger.log(Level.SEVERE, "get failed", re);
            throw re;
        }
    }

    public List<MessageOffreemploi> findAll()
    {
        Query query = entityManager.createQuery("select messageOffreemploi from MessageOffreemploi messageOffreemploi " +
                "order by messageOffreemploi.idMessageOffre desc");
        List<MessageOffreemploi> l = query.getResultList();
        return l;
    }

}

