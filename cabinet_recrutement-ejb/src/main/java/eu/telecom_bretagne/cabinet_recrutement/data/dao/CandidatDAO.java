package eu.telecom_bretagne.cabinet_recrutement.data.dao;
// Generated Feb 2, 2023, 3:05:57 PM by Hibernate Tools 5.4.20.Final


import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;
import eu.telecom_bretagne.cabinet_recrutement.data.model.*;

/**
 * Home object for domain model class Candidat.
 * @see eu.telecom_bretagne.cabinet_recrutement.data.model.Candidat
 * @author Hibernate Tools
 */
@Stateless
public class CandidatDAO {

    private static final Logger logger = Logger.getLogger(CandidatDAO.class.getName());

    @PersistenceContext private EntityManager entityManager;
    
    public Candidat persist(Candidat transientInstance) {
        logger.log(Level.INFO, "persisting Candidat instance");
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
    
    public void remove(Candidat persistentInstance) {
        logger.log(Level.INFO, "removing Candidat instance");
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
    
    public Candidat merge(Candidat detachedInstance) {
        logger.log(Level.INFO, "merging Candidat instance");
        try {
            Candidat result = entityManager.merge(detachedInstance);
            logger.log(Level.INFO, "merge successful");
            return result;
        }
        catch (RuntimeException re) {
            logger.log(Level.SEVERE, "merge failed", re);
            throw re;
        }
    }

    public Candidat update(Candidat candidat) {
        if (candidat != null) {
            entityManager.merge(candidat);
        }
        return candidat;
    }
    
    public Candidat findById( int id) {
        logger.log(Level.INFO, "getting Candidat instance with id: " + id);
        try {
            Candidat instance = entityManager.find(Candidat.class, id);
            logger.log(Level.INFO, "get successful");
            return instance;
        }
        catch (RuntimeException re) {
            logger.log(Level.SEVERE, "get failed", re);
            throw re;
        }
    }

    public List<Candidat> findBySecteurActiviteAndNiveauQualification(int idSecteurActivite, int idNiveauQualification)
    {
        Query query = entityManager.createQuery("select c from Candidat c join c.secteurActivites secteur " +
                "where secteur.idSecteur = :idSA and c.niveauQualification.idQualification = :idNQ " +
                "order by c.idCandidat desc");
        query.setParameter("idSA", idSecteurActivite);
        query.setParameter("idNQ", idNiveauQualification);
        List<Candidat> l = query.getResultList();
        return l;
    }

    public List<Candidat> findAll()
    {
        Query query = entityManager.createQuery("select candidat from Candidat candidat " +
                "order by candidat.idCandidat desc");
        List<Candidat> l = query.getResultList();
        return l;
    }
}

