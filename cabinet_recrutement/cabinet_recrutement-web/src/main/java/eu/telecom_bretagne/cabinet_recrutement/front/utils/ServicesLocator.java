package eu.telecom_bretagne.cabinet_recrutement.front.utils;

import java.util.HashMap;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ServicesLocator {
    //-----------------------------------------------------------------------------
    private Context initialContext;
    private Map<String, Object> cache;
    // Singleton
    private static final ServicesLocator instance = new ServicesLocator();

    //-----------------------------------------------------------------------------
    private ServicesLocator() {
        try {
            initialContext = new InitialContext();
            cache = new HashMap<String, Object>();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    //-----------------------------------------------------------------------------
    public static ServicesLocator getInstance() {
        return instance;
    }
    //-----------------------------------------------------------------------------

    /**
     * Renvoie le stub du composant EJB.
     *
     * @param nomEJB nom JNDI du composant EJB recherché
     * @return le stub du composant EJB correspondant au nom JNDI.
     * @throws ServicesLocatorException levé en cas de problèmes liés à la recherche.
     */
    public Object getRemoteInterface(String nomEJB) throws ServicesLocatorException {
        // Le nom JNDI pour la récupération du service distant (stub du
        // composant EJB) est de la forme :
        //   java:global/<nom projet EAR>/<nom sous-projet EJB>/<nom bean session EJB>!<nom complet avec package de l'interface remote du bean>
        // Exemple :
        //   java:global/CabinetRecrutement_EAR/CabinetRecrutement_EJB/ServiceEntreprise!eu.telecom_bretagne.cabinet_recrutement.service.IServiceEntreprise

        String nomJNDI = null;
        if (nomEJB.equals("ServiceEntreprise"))
            nomJNDI = "java:global/cabinet_recrutement-ear/eu.telecom_bretagne.cabinet_recrutement-cabinet_recrutement-web-1.0-SNAPSHOT/ServiceEntreprise!eu.telecom_bretagne.cabinet_recrutement.service.IServiceEntreprise";
//      nomJNDI = "java:global/CabinetRecrutement/CabinetRecrutement_EJB/ServiceEntreprise!eu.telecom_bretagne.cabinet_recrutement.service.IServiceEntreprise";
        else if (nomEJB.equals("Service_XXX"))
            nomJNDI = "*** À compléter ***";
        else if (nomEJB.equals("ServiceCandidat"))
            nomJNDI = "java:global/cabinet_recrutement-ear/eu.telecom_bretagne.cabinet_recrutement-cabinet_recrutement-web-1.0" +
                    "-SNAPSHOT/ServiceCandidat!eu.telecom_bretagne.cabinet_recrutement.service.IServiceCandidat";
        else if (nomEJB.equals("ServiceMessageCandidat"))
            nomJNDI = "java:global/cabinet_recrutement-ear/eu.telecom_bretagne.cabinet_recrutement-cabinet_recrutement-web-1.0" +
                    "-SNAPSHOT/ServiceMessageCandidat!eu.telecom_bretagne.cabinet_recrutement.service.IServiceMessageCandidat";
        else if (nomEJB.equals("ServiceMessageCandidat"))
            nomJNDI = "java:global/cabinet_recrutement-ear/eu.telecom_bretagne.cabinet_recrutement-cabinet_recrutement-web-1.0" +
                    "-SNAPSHOT/ServiceMessageCandidat!eu.telecom_bretagne.cabinet_recrutement.service.IServiceMessageCandidat";
        else if (nomEJB.equals("ServiceMessageOffreemploi"))
            nomJNDI = "java:global/cabinet_recrutement-ear/eu.telecom_bretagne.cabinet_recrutement-cabinet_recrutement-web-1.0" +
                    "-SNAPSHOT/ServiceMessageOffreemploi!eu.telecom_bretagne.cabinet_recrutement.service.IServiceMessageOffreemploi";
        else if (nomEJB.equals("ServiceNiveauQualification"))
            nomJNDI = "java:global/cabinet_recrutement-ear/eu.telecom_bretagne.cabinet_recrutement-cabinet_recrutement-web-1.0" +
                    "-SNAPSHOT/ServiceNiveauQualification!eu.telecom_bretagne.cabinet_recrutement.service.IServiceNiveauQualification";
        else if (nomEJB.equals("ServiceOffreEmploi"))
            nomJNDI = "java:global/cabinet_recrutement-ear/eu.telecom_bretagne.cabinet_recrutement-cabinet_recrutement-web-1.0" +
                    "-SNAPSHOT/ServiceOffreEmploi!eu.telecom_bretagne.cabinet_recrutement.service.IServiceOffreEmploi";
        else if (nomEJB.equals("ServiceSecteurActivite"))
            nomJNDI = "java:global/cabinet_recrutement-ear/eu.telecom_bretagne.cabinet_recrutement-cabinet_recrutement-web-1.0" +
                    "-SNAPSHOT/ServiceSecteurActivite!eu.telecom_bretagne.cabinet_recrutement.service.IServiceSecteurActivite";
            //nomJNDI = "java:global/cabinet_recrutement-ear/eu.telecom_bretagne.cabinet_recrutement-cabinet_recrutement-web-1.0" +
            //      "-SNAPSHOT/ServiceNomService!eu.telecom_bretagne.cabinet_recrutement.service.IServiceNomService";

            // ATTENTION !!! La récupération d'un DAO n'existe ici que
            // pour les contrôles (utilisés dans la servlet ControleDAOServlet) :
            // ils ne sont normalement pas appelés par la couche IHM.

        else if (nomEJB.equals("EntrepriseDAO"))
            nomJNDI = "java:global/cabinet_recrutement-ear/eu.telecom_bretagne.cabinet_recrutement-cabinet_recrutement-web-1.0-SNAPSHOT/EntrepriseDAO";
        else if (nomEJB.equals("CandidatDAO"))
            nomJNDI = "java:global/cabinet_recrutement-ear/eu.telecom_bretagne.cabinet_recrutement-cabinet_recrutement-web-1.0-SNAPSHOT/CandidatDAO";
        else if (nomEJB.equals("MessageCandidatDAO"))
            nomJNDI = "java:global/cabinet_recrutement-ear/eu.telecom_bretagne.cabinet_recrutement-cabinet_recrutement-web-1.0-SNAPSHOT/MessageCandidatDAO";
        else if (nomEJB.equals("MessageOffreemploiDAO"))
            nomJNDI = "java:global/cabinet_recrutement-ear/eu.telecom_bretagne.cabinet_recrutement-cabinet_recrutement-web-1.0-SNAPSHOT/MessageOffreemploiDAO";
        else if (nomEJB.equals("NiveauQualificationDAO"))
            nomJNDI = "java:global/cabinet_recrutement-ear/eu.telecom_bretagne.cabinet_recrutement-cabinet_recrutement-web-1.0-SNAPSHOT/NiveauQualificationDAO";
        else if (nomEJB.equals("OffreEmploiDAO"))
            nomJNDI = "java:global/cabinet_recrutement-ear/eu.telecom_bretagne.cabinet_recrutement-cabinet_recrutement-web-1.0-SNAPSHOT/OffreEmploiDAO";
        else if (nomEJB.equals("SecteurActiviteDAO"))
            nomJNDI = "java:global/cabinet_recrutement-ear/eu.telecom_bretagne.cabinet_recrutement-cabinet_recrutement-web-1.0-SNAPSHOT/SecteurActiviteDAO";
//      nomJNDI = "java:global/CabinetRecrutement/CabinetRecrutement_EJB/EntrepriseDAO!eu.telecom_bretagne.cabinet_recrutement.data.dao.EntrepriseDAO";

        else
            throw new ServicesLocatorException("Il n'y a pas d'EJB avec ce nom...");

        // La méthode recherche d'abord le stub dans le cache, s'il est absent,
        // il est récupéré via JNDI.
        Object remoteInterface = cache.get(nomJNDI);
        if (remoteInterface == null) {
            try {
                remoteInterface = initialContext.lookup(nomJNDI);
                cache.put(nomJNDI, remoteInterface);
            } catch (Exception e) {
                throw new ServicesLocatorException(e);
            }
        }
        return remoteInterface;
    }
    //-----------------------------------------------------------------------------
}
