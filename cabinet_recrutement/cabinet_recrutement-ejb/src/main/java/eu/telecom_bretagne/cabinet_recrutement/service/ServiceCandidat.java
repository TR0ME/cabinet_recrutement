
package eu.telecom_bretagne.cabinet_recrutement.service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import eu.telecom_bretagne.cabinet_recrutement.data.model.*;
import jakarta.ejb.EJB;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;

import eu.telecom_bretagne.cabinet_recrutement.data.dao.CandidatDAO;
import eu.telecom_bretagne.cabinet_recrutement.data.dao.*;

/**
 * Session Bean implementation class ServiceEntreprise
 *
 * @author Elouan LE DUC
 * @author Clement LE GRUIEC
 */
@Stateless
@LocalBean
public class ServiceCandidat extends ServicesGlobal implements IServiceCandidat {
    //-----------------------------------------------------------------------------
    @EJB
    private CandidatDAO candidatureDAO;
    @EJB
    private OffreEmploiDAO offreemploiDAO;
    @EJB
    private NiveauQualificationDAO niveauqualificationDAO;
    @EJB
    private SecteurActiviteDAO secteuractiviteDAO;
    @EJB
    private MessageCandidatDAO messagecandidatureDAO;
    @EJB
    private MessageOffreemploiDAO messageoffredemploiDAO;
    //-----------------------------------------------------------------------------

    /**
     * Default constructor.
     */
    public ServiceCandidat() {
        // TODO Auto-generated constructor stub
    }

    //-----------------------------------------------------------------------------
    @Override
    public Candidat getCandidat(int id) {
        return candidatureDAO.findById(id);
    }

    //-----------------------------------------------------------------------------
    @Override
    public List<Candidat> listeCandidat() {
        return candidatureDAO.findAll();
    }

    //-----------------------------------------------------------------------------
    @Override
    public NiveauQualification findNQByID(Integer id) {
        return niveauqualificationDAO.findById(id);
    }

    //-----------------------------------------------------------------------------

    @Override
    public String GetSecteursString(Candidat cand) {
        String SecteursToString = "";

        try {
            for (SecteurActivite secteurs_recup : cand.getSecteurActivites()) {
                SecteursToString += secteurs_recup.getIntituleActivite() + "<br>";
                //System.out.println(secteurs_recup.getIntitule());
            }
        } catch (Exception e) {
            System.out.println("---------------- cassé get secteur acti ServiceCandidature");
        }

        return SecteursToString;
    }

    //-----------------------------------------------------------------------------
    public Candidat execPersist(Candidat candidature) {
        Candidat c = candidatureDAO.persist(candidature);
        return c;
    }

    //-----------------------------------------------------------------------------
    public Candidat execUpdate(Candidat candidature) {
        Candidat c = candidatureDAO.update(candidature);
        return c;
    }

    //-----------------------------------------------------------------------------
    public void majSecteursActivites(String[] sects, int idC) {
        SecteurActivite s;
        Candidat c;
        c = candidatureDAO.findById(idC);
        //System.out.println("-------------> idC = "+idC);
        for (String sect : sects) {
            try {
                s = secteuractiviteDAO.findById(Integer.parseInt(sect));
                s.getCandidats().add(c);
                secteuractiviteDAO.update(s);
                c.getSecteurActivites().add(s);
                candidatureDAO.update(c);
            } catch (Exception e) {
                System.out.println("---------------> majDuSecteurDansCandErreur");
            }


        }
    }

    //-----------------------------------------------------------------------------
    public Candidat RAZsecteurs(int idC) {
        Candidat c = candidatureDAO.findById(idC);
        Set<SecteurActivite> sas = c.getSecteurActivites();
        try {
            for (SecteurActivite sa : sas) {
                System.out.println("rm-----" + sa.getIntituleActivite());
                sa.getCandidats().remove(c);
                secteuractiviteDAO.update(sa);
            }

            c.getSecteurActivites().clear();
            c = candidatureDAO.update(c);

        } catch (Exception e) {
            System.out.println("RAZ cassé");
            System.out.println(e);
        }
        return c;

    }

    //-----------------------------------------------------------------------------
    public Set<SecteurActivite> transformSecteurs(String[] sect) {
        //System.out.println(sect[0]+""+sect[1]);
        Set<SecteurActivite> mySet = new HashSet<SecteurActivite>();
        for (String s : sect) {
            mySet.add(secteuractiviteDAO.findById(Integer.parseInt(s)));
            //System.out.println(secteuractiviteDAO.findById(Integer.parseInt(sect[i])).getIntitule());
        }
        return mySet;
    }

    //-----------------------------------------------------------------------------
    public Boolean doesSectorExist(Set<SecteurActivite> sects, int id) {
        for (SecteurActivite s : sects) {
            if (s.getIdSecteur() == id) {
                return true;
            }
        }
        return false;
    }

    //-----------------------------------------------------------------------------
    public void supressionDunCandidat(Candidat c) {
        try {
            //suppression des messages candidatures
            for (MessageCandidat msgC : c.getMessageCandidats()) {
                msgC.getOffreEmploi().removeMessageCandidat(msgC);
                offreemploiDAO.update(msgC.getOffreEmploi());
                messagecandidatureDAO.remove(msgC);
            }
            System.out.println("---------------ok 1 ");
            //suppression des messages offresEmplois
            for (MessageOffreemploi msgOF : c.getMessageOffreemplois()) {
                msgOF.getOffreEmploi().removeMessageOffredemploi(msgOF);
                messageoffredemploiDAO.remove(msgOF);
            }
            System.out.println("---------------ok 2 ");
            //suppression dans les secteur activite
            c.getNiveauQualification().removeCandidat(c);
            System.out.println("---------------ok 3 ");
            //suppression dans le niveau qualification
            for (SecteurActivite sa : c.getSecteurActivites()) {
                if (c != null) sa.getCandidats().remove(c);
            }
            System.out.println("---------------ok 4 ");
            //suppression des messages offresEmplois
            candidatureDAO.remove(c);
            System.out.println("---------------ok 5 ");

        } catch (Exception e) {

            System.out.println("--------suppression candidature kassé");
            System.out.println(e);
        }

    }
}