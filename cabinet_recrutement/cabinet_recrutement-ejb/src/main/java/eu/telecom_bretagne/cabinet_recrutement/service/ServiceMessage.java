package eu.telecom_bretagne.cabinet_recrutement.service;

import java.util.ArrayList;
import java.util.List;

import jakarta.ejb.EJB;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.jws.WebService;

import eu.telecom_bretagne.cabinet_recrutement.data.dao.*;
import eu.telecom_bretagne.cabinet_recrutement.data.model.*;

/**
 * Session Bean implementation class ServiceEntreprise
 * @author Mathieu Bourges
 * @author Laure Andro
 *
 */
@Stateless
@LocalBean
public class ServiceMessage implements IServiceMessage
{
    //-----------------------------------------------------------------------------
    @EJB private MessageCandidatDAO messageCandidatDAO;
    @EJB private MessageOffreemploiDAO messageOffreemploiDAO;
    //-----------------------------------------------------------------------------
    /**
     * Default constructor.
     */
    public ServiceMessage()
    {
        // TODO Auto-generated constructor stub
    }
    //-----------------------------------------------------------------------------
    @Override
    public MessageCandidat getMessageCandidatById(int id) {
        return messageCandidatDAO.findById(id);
    }

    @Override
    public MessageOffreemploi getMessageOffreEmploiById(int id) {
        return messageOffreemploiDAO.findById(id);
    }


}