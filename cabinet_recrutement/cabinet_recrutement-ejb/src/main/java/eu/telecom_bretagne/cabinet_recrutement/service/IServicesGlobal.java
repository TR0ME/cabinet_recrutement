package eu.telecom_bretagne.cabinet_recrutement.service;

import java.sql.Date;
import java.util.List;

import jakarta.ejb.Remote;

import eu.telecom_bretagne.cabinet_recrutement.data.model.Candidat;
import eu.telecom_bretagne.cabinet_recrutement.data.model.Entreprise;
import eu.telecom_bretagne.cabinet_recrutement.data.model.NiveauQualification;
import eu.telecom_bretagne.cabinet_recrutement.data.model.SecteurActivite;

/**
 * Interface du service gérant les fonctions generiques.
 * @author Elouan LE DUC
 * @author Clement LE GRUIEC
 *
 */
@Remote
public interface IServicesGlobal {
    //-----------------------------------------------------------------------------
    /**
     * Obtention de la date actuelle.
     *
     * @return Date
     */
    public Date getCurrentDate();
    //-----------------------------------------------------------------------------
    /**
     * Converti un string en objet date
     *
     * @return Date
     */
    public Date convertDate(String date);
    //-----------------------------------------------------------------------------
    /**
     * Converti une Date en String
     *
     * @return String
     */
    public String convertDatetoString(java.util.Date date);
    //-----------------------------------------------------------------------------
    /**
     * liste les niveau de qualification
     *
     * @return List<NiveauQualification>
     */
    public List<NiveauQualification> listeNiveauQualification();
    //-----------------------------------------------------------------------------
    /**
     * liste les secteurs
     *
     * @return List<SecteurActivite>
     */
    public List<SecteurActivite> listeSecteurs();

}