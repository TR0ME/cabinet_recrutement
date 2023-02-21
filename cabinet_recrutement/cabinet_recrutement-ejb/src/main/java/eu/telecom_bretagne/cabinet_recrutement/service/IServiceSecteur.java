package eu.telecom_bretagne.cabinet_recrutement.service;

import java.util.List;

import jakarta.ejb.Remote;

import eu.telecom_bretagne.cabinet_recrutement.data.model.SecteurActivite;

/**
 * Interface du service gérant les SecteurActivite.
 * @author Mathieu Bourges
 * @author Laure Andro
 *
 */
@Remote
public interface IServiceSecteur {
    // -----------------------------------------------------------------------------
    /**
     * Obtention d'une <{@link SecteurActivite}.
     *
     * @param id id de SecteurActivite à récupérer.
     * @return
     */
    public SecteurActivite getSecteurActivite(int id);

    /**
     * Obtention de la liste de toutes les SecteurActivite.
     *
     * @return la liste des SecteurActivite dans une {@code List<SecteurActivite>}.
     */
    public List<SecteurActivite> listeDesSecteurs();

    // -----------------------------------------------------------------------------
    /**
     * Permet d'envoyer le tuble dans la table
     *
     * @return l'objet SecteurActivite créé
     */
    public SecteurActivite execPersist(SecteurActivite SA);
//-----------------------------------------------------------------------------
}