package eu.telecom_bretagne.cabinet_recrutement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import jakarta.ejb.EJB;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;

import eu.telecom_bretagne.cabinet_recrutement.data.dao.*;

import eu.telecom_bretagne.cabinet_recrutement.data.model.*;


/**
 * Session Bean implementation class ServiceEntreprise
 *
 * @author Mathieu Bourges
 * @author Laure Andro
 */
@Stateless
@LocalBean
public class ServiceOffreEmploi extends ServicesGlobal implements IServiceOffreEmploi {
    //-----------------------------------------------------------------------------
    @EJB
    private CandidatDAO candidatDAO;
    @EJB
    private OffreEmploiDAO offreEmploiDAO;
    @EJB
    private NiveauQualificationDAO niveauQualificationDAO;
    @EJB
    private SecteurActiviteDAO secteurActiviteDAO;
    //-----------------------------------------------------------------------------

    /**
     * Default constructor.
     */
    public ServiceOffreEmploi() {
        // TODO Auto-generated constructor stub
    }

    //-----------------------------------------------------------------------------
    @Override
    public OffreEmploi getOffreEmploi(int id) {

        return offreEmploiDAO.findById(id);
    }

    //-----------------------------------------------------------------------------
    @Override
    public List<OffreEmploi> listeOffreEmploi() {

        return offreEmploiDAO.findAll();
    }
    //-----------------------------------------------------------------------------
    public void majSecteursActivites(String[] secteurs, int iDOffre) {
        SecteurActivite s;
        OffreEmploi of;
        of = offreEmploiDAO.findById(iDOffre);
        //System.out.println("-------------> idC = "+idC);
        for (String sect : secteurs) {
            try {
                s = secteurActiviteDAO.findById(Integer.parseInt(sect));
                s.getOffreEmplois().add(of);
                secteurActiviteDAO.update(s);
                of.getSecteurActivites().add(s);
                offreEmploiDAO.update(of);
            } catch (Exception e) {
                System.out.println("[ERROR]MAJ secteur ACT dans offre emploi");
                e.printStackTrace(System.out);
            }
        }
    }

    //-----------------------------------------------------------------------------
    @Override
    public List<OffreEmploi> getEmploiBySectorAndNQ(Set<SecteurActivite> IDsSect, NiveauQualification idNQ) {
        List<OffreEmploi> l = new ArrayList<>();
        for (SecteurActivite s : IDsSect) {
            System.out.println("--------------" + s.getIdSecteur() + "--------" + idNQ.getIdQualification() + "---------------");
            l.addAll(offreEmploiDAO.findBySecteurActiviteAndNiveauQualification(s.getIdSecteur(), idNQ.getIdQualification()));
        }
        return l;
    }

    //-----------------------------------------------------------------------------
    @Override
    public String GetSecteursString(OffreEmploi offres) {
        String SecteursToString = "";

        try {
            for (SecteurActivite secteurs_recup : offres.getSecteurActivites()) {
                SecteursToString += secteurs_recup.getIntituleActivite() + "<br>";
                //System.out.println(secteurs_recup.getIntitule());
            }
        } catch (Exception e) {
            System.out.println("[ERROR]Récupération des Secteur Activite dans service Offre");
            e.printStackTrace(System.out);
        }

        return SecteursToString;
    }

    //----------------------------------------------------------------------------
    public NiveauQualification findNQByID(Integer id) {

        return niveauQualificationDAO.findById(id);
    }

    public OffreEmploi execPersist(OffreEmploi oe) {
        OffreEmploi offre = offreEmploiDAO.persist(oe);
        return offre;
    }

    //-----------------------------------------------------------------------------
    public OffreEmploi updateOffreEmploi(OffreEmploi offreEmploi) {
        OffreEmploi offre = offreEmploiDAO.update(offreEmploi);
        return offre;
    }

    //-----------------------------------------------------------------------------

}