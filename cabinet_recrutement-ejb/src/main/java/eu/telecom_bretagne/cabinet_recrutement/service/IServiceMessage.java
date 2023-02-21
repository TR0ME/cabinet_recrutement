package eu.telecom_bretagne.cabinet_recrutement.service;

import java.util.List;

import jakarta.ejb.Remote;

import eu.telecom_bretagne.cabinet_recrutement.data.model.*;



/**
 * Interface du service gérant les Messages.
 * @author Mathieu Bourges
 * @author Laure Andro
 *
 */
@Remote
public interface IServiceMessage
{
    /**
     * permet de récupérer un message
     *
     * @param id du message candidature
     *
     * @return MessageCandidature
     */
    public MessageCandidat getMessageCandidatById(int id);
    /**
     * permet de récupérer un message
     *
     * @param id du message offre emploi
     *
     * @return MessageOffredemploi
     */
    public MessageOffreemploi getMessageOffreEmploiById(int id);

}