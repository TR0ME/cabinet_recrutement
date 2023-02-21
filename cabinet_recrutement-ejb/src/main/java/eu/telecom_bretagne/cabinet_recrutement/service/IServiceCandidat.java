package eu.telecom_bretagne.cabinet_recrutement.service;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import jakarta.ejb.Remote;

import eu.telecom_bretagne.cabinet_recrutement.data.model.Candidat;
import eu.telecom_bretagne.cabinet_recrutement.data.model.Entreprise;
import eu.telecom_bretagne.cabinet_recrutement.data.model.NiveauQualification;
import eu.telecom_bretagne.cabinet_recrutement.data.model.OffreEmploi;
import eu.telecom_bretagne.cabinet_recrutement.data.model.SecteurActivite;

/**
 * Interface du service gérant les candidature.
 * @author Mathieu Bourges
 * @author Laure Andro
 *
 */
@Remote
public interface IServiceCandidat extends IServicesGlobal
{
    //-----------------------------------------------------------------------------
    /**
     * Obtention d'une candidature recherché.
     *
     * @param id id de la candidature à récupérer.
     * @return Candidature
     */
    public Candidat getCandidat(int id);
    /**
     * Obtention de la liste de toutes les entreprises.
     *
     * @return la liste des entreprises dans une {@code List<Entreprise>}.
     */
    public List<Candidat> listeCandidat();
    /**
     * Obtention du NQ recherché.
     *
     * @param id id du niveau qualification à récupérer.
     *
     * @return NQ
     */
    public NiveauQualification findNQByID(Integer id);
    /**
     * Permet de persister la candidature
     *
     * @param candidat à persister.
     *
     * @return Candidature
     */
    public Candidat execPersist(Candidat candidat);
    /**
     * Permet de maj la candidature
     *
     * @param candidat Objet à maj.
     *
     * @return Candidature
     */
    public Candidat execUpdate(Candidat candidat);
    /**
     * Transforme une liste de secteur en string
     *
     * @param cand .
     *
     * @return String[] liste des secteurs d'activité
     */
    public String GetSecteursString(Candidat cand);
    /**
     * Transforme une liste de string en secteur
     *
     * @param sect liste des secteur en string.
     *
     * @return Secteurs
     */
    public Set<SecteurActivite> transformSecteurs(String[] sect);
    /**
     * Permet de d'ajouter des secteurs à une candidature
     *
     * @param idC id de la candidature, sects liste des secteur sous forme de string
     *
     * @return Candidature
     */
    public void majSecteursActivites(String[] sects, int idC);
    /**
     * Verifie l'existence de secteur dans une candidature
     *
     * @param sects à vérifier, id de la candidature
     *
     * @return Candidature
     */
    public Boolean doesSectorExist(Set<SecteurActivite> sects, int id);
    /**
     * Permet de supprimer une une candidature
     *
     * @param c .
     *
     */
    public void supressionDunCandidat(Candidat c);
    /**
     * Permet de remettre à zero les secteurs d'activités
     *
     * @param idC id de la candidature Objet.
     *
     * @return Candidature
     */
    public Candidat RAZsecteurs(int idC);
}