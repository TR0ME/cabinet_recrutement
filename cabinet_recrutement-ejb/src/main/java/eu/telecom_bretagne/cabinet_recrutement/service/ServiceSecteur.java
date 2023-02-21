package eu.telecom_bretagne.cabinet_recrutement.service;

import java.util.List;

import jakarta.ejb.EJB;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;

import eu.telecom_bretagne.cabinet_recrutement.data.dao.SecteurActiviteDAO;
import eu.telecom_bretagne.cabinet_recrutement.data.model.SecteurActivite;

/**
 * Session Bean implementation class ServiceSecteur
 *
 * @author Mathieu Bourges
 * @author Laure Andro
 */
@Stateless
@LocalBean
public class ServiceSecteur implements IServiceSecteur {
    //-----------------------------------------------------------------------------
    @EJB
    private SecteurActiviteDAO secteurActiviteDAO;
    //-----------------------------------------------------------------------------

    /**
     * Default constructor.
     */
    public ServiceSecteur() {
        // TODO Auto-generated constructor stub
    }

    //-----------------------------------------------------------------------------
    @Override
    public SecteurActivite getSecteurActivite(int id) {

        return secteurActiviteDAO.findById(id);
    }

    //-----------------------------------------------------------------------------
    @Override
    public List<SecteurActivite> listeDesSecteurs() {

        return secteurActiviteDAO.findAll();
    }

    //-----------------------------------------------------------------------------
    @Override
    public SecteurActivite execPersist(SecteurActivite SA) {

        return secteurActiviteDAO.persist(SA);
    }
    //-----------------------------------------------------------------------------
}