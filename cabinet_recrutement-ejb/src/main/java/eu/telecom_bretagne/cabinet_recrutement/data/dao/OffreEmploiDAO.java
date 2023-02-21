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
 * Home object for domain model class OffreEmploi.
 * @see eu.telecom_bretagne.cabinet_recrutement.data.model.OffreEmploi
 * @author Hibernate Tools
 */
@Stateless
public class OffreEmploiDAO {

    private static final Logger logger = Logger.getLogger(OffreEmploiDAO.class.getName());

    @PersistenceContext private EntityManager entityManager;
    
    public OffreEmploi persist(OffreEmploi transientInstance) {
        logger.log(Level.INFO, "persisting OffreEmploi instance");
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
    
    public void remove(OffreEmploi persistentInstance) {
        logger.log(Level.INFO, "removing OffreEmploi instance");
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
    
    public OffreEmploi merge(OffreEmploi detachedInstance) {
        logger.log(Level.INFO, "merging OffreEmploi instance");
        try {
            OffreEmploi result = entityManager.merge(detachedInstance);
            logger.log(Level.INFO, "merge successful");
            return result;
        }
        catch (RuntimeException re) {
            logger.log(Level.SEVERE, "merge failed", re);
            throw re;
        }
    }

    public OffreEmploi update(OffreEmploi of) {
        if (of != null) {
            entityManager.merge(of);
        }
        return of;
    }
    
    public OffreEmploi findById( int id) {
        logger.log(Level.INFO, "getting OffreEmploi instance with id: " + id);
        try {
            OffreEmploi instance = entityManager.find(OffreEmploi.class, id);
            logger.log(Level.INFO, "get successful");
            return instance;
        }
        catch (RuntimeException re) {
            logger.log(Level.SEVERE, "get failed", re);
            throw re;
        }
    }

    public List<OffreEmploi> findByEntreprise(int idEntreprise)
    {
        Query query = entityManager.createQuery("select offreEmploi from OffreEmploi offreEmploi " +
                "where offreEmploi.entreprise.id = :idE " +
                "order by offreEmploi.idOffre desc");
        query.setParameter("idE", idEntreprise);
        List<OffreEmploi> l = query.getResultList();
        return l;
    }

    public List<OffreEmploi> findBySecteurActiviteAndNiveauQualification(int idSecteurActivite,
                                                                         int idNiveauQualification)
    {
        Query query = entityManager.createQuery("select oe from OffreEmploi oe join oe.secteurActivites sects " +
                "where sects.idSecteur = :idSA and oe.niveauQualification.idQualification = :idNQ " +
                "order by oe.idOffre desc");
        query.setParameter("idSA", idSecteurActivite);
        query.setParameter("idNQ", idNiveauQualification);
        List<OffreEmploi> l = query.getResultList();
        return l;
    }

    public List<OffreEmploi> findAll()
    {
        Query query = entityManager.createQuery("select offreEmploi from OffreEmploi offreEmploi " +
                "order by offreEmploi.idOffre desc");
        List<OffreEmploi> l = query.getResultList();
        return l;
    }
}

