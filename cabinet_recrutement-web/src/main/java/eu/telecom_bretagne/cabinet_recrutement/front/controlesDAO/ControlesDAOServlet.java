package eu.telecom_bretagne.cabinet_recrutement.front.controlesDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

import eu.telecom_bretagne.cabinet_recrutement.data.dao.*;
import eu.telecom_bretagne.cabinet_recrutement.data.model.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import eu.telecom_bretagne.cabinet_recrutement.front.utils.ServicesLocator;
import eu.telecom_bretagne.cabinet_recrutement.front.utils.ServicesLocatorException;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/ControlesDAO")
public class ControlesDAOServlet extends HttpServlet {
    //-----------------------------------------------------------------------------
    private static final long serialVersionUID = 1L;
    //-----------------------------------------------------------------------------
    private int idTest = 0; //Id qui est utilisé lors des tests

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControlesDAOServlet() {
        super();
    }
    //-----------------------------------------------------------------------------

    /**
     * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
     */
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Flot de sortie pour écriture des résultats.
        PrintWriter out = response.getWriter();
        String cl, methode;


        //---------TEST des Entreprises--------------
        cl = "Entreprise";


        // Récupération de la réféence vers le(s) DAO(s)
        EntrepriseDAO entrepriseDAO = null;
        try {
            entrepriseDAO = (EntrepriseDAO) ServicesLocator.getInstance().getRemoteInterface("EntrepriseDAO");
        } catch (ServicesLocatorException e) {
            e.printStackTrace();
        }
        generateurEntreprise(entrepriseDAO, out);
        out.println("[INFO]Contrôles de fonctionnement du DAO EntrepriseDAO");
        out.println();

        // Contrôle(s) de fonctionnalités.
        //Affichage des entreprises
        out.println("[INFO]Liste des entreprises :");
        List<Entreprise> entreprises = entrepriseDAO.findAll();

        for (Entreprise entreprise : entreprises) {
            out.println(entreprise.getNom());
        }
        out.println();
        try {
            out.println("[INFO]Obtention de l'entreprise n° 1 :");
            Entreprise e = entrepriseDAO.findById(1);
            out.println(e.getId());
            out.println(e.getNom());
            out.println(e.getDescriptif());
            out.println(e.getAdressePostale());
            out.println();

            out.println("[INFO]Obtention de l'entreprise n° 2 :");
            e = entrepriseDAO.findById(2);
            out.println(e.getId());
            out.println(e.getNom());
            out.println(e.getDescriptif());
            out.println(e.getAdressePostale());
            out.println();

        } catch (Exception e) {
            out.println("[ERROR]Lecture entrepises existantes");

        }

        try {
            //Test de la création d'une nouvelle entreprise
            Entreprise entreprise_test = new Entreprise("Bureau a coté de la 114", "Incompétents", "PSF");
            entreprise_test = entrepriseDAO.persist(entreprise_test);
            idTest = -1;
            idTest = entreprise_test.getId();
            Entreprise entreprise_recup = entrepriseDAO.findById(idTest);
            if ((entreprise_test.getId() == entreprise_recup.getId()) && (entreprise_test.getNom().equals(entreprise_recup.getNom()))
                    && (entreprise_test.getDescriptif().equals(entreprise_recup.getDescriptif()))
                    && (entreprise_test.getAdressePostale().equals(entreprise_recup.getAdressePostale()))) {
                out.println("[OK]Ajout et Recup " + cl);
            } else {
                out.println("[ERROR]ajout et recup " + cl);
            }


            //Test de la modification d'une entreprise
            //Modification de l'adresse
            out.println("[INFO]modification des paramètres d'une entreprise");
            entreprise_recup.setAdressePostale("Dans le local poubelle");
            entrepriseDAO.update(entreprise_recup);
            entreprise_recup = entrepriseDAO.findById(idTest);
            methode = "adressePostale";
            compare(entreprise_test.getAdressePostale(), entreprise_recup.getAdressePostale(), cl, methode, out);

            //Modification du nom

            entreprise_recup.setNom("Pole Stage et Formation");
            entrepriseDAO.update(entreprise_recup);
            entreprise_recup = entrepriseDAO.findById(idTest);
            methode = "Nom";
            compare(entreprise_test.getNom(), entreprise_recup.getNom(), cl, methode, out);


            //Modification de la description
            entreprise_recup.setDescriptif("Ce sont des secrétaires fictives");
            entrepriseDAO.update(entreprise_recup);
            entreprise_recup = entrepriseDAO.findById(idTest);
            methode = "descriptif";
            compare(entreprise_test.getDescriptif(), entreprise_recup.getDescriptif(), cl, methode, out);

            //Test de la supression
            out.println("[INFO]Suppression de l'entreprise de test");
            methode = "supprimer";
            entrepriseDAO.remove(entreprise_recup);
            testSupression(entrepriseDAO.findById(idTest), cl, methode, out);
            out.println();
        } catch (Exception e) {
            out.println("[ERROR]Création/Insertion/Suppression entreprise");
            e.printStackTrace();
        }
        out.println("----------------------------------------------------------------");


        //----------------Test du niveau de qualification ----------------
        cl = "niveau Qualification";
        out.println("[INFO]Test niveau qualification");
        NiveauQualificationDAO niveauqualificationDAO = null;
        try {
            niveauqualificationDAO = (NiveauQualificationDAO) ServicesLocator.getInstance().getRemoteInterface("NiveauQualificationDAO");
        } catch (ServicesLocatorException ex) {
            throw new RuntimeException(ex);
        }
        out.println("[OK]Niveau qualification DAO crée");
        out.println();

        //Liste niv qualification
        out.println("[INFO]Liste des niveau de qualification : ");
        List<NiveauQualification> niveauqualifications = niveauqualificationDAO.findAll();
        for (NiveauQualification niveau : niveauqualifications) {
            out.println(niveau.toStringShort());
        }
        out.println();
        try {
            //Test de recup des info à la mano pour le niveau de qualification
            out.println("Niveau de qualilification n°1");
            NiveauQualification niveauqualification = niveauqualificationDAO.findById(1);
            out.println("ID : " + niveauqualification.getIdQualification());
            out.println("Intitulé : " + niveauqualification.getIntituleQualification());

            out.println("Niveau de qualilification n°2");
            niveauqualification = niveauqualificationDAO.findById(2);
            out.println("ID : " + niveauqualification.getIdQualification());
            out.println("Intitulé : " + niveauqualification.getIntituleQualification());

            out.println("Niveau de qualilification n°3");
            niveauqualification = niveauqualificationDAO.findById(3);
            out.println("ID : " + niveauqualification.getIdQualification());
            out.println("Intitulé : " + niveauqualification.getIntituleQualification());
        } catch (Exception e) {
            out.println("[ERROR]Lors de la lecture de niveau de qualification");
            //e.printStackTrace(out);
        }

        //Test ajout niveau qualification
        NiveauQualification niv_test = new NiveauQualification("Astronaute");
        NiveauQualification niv_recup = null;
        idTest = -1;
        out.println("[INFO]Ajout Niveau de qualification");
        try {
            niv_test = niveauqualificationDAO.persist(niv_test);
            idTest = niv_test.getIdQualification();

            niv_recup = niveauqualificationDAO.findById(idTest);
            /*out.println("--------------------------");
            out.println("ID recu qualif : "+idTest);
            out.println(niv_test.toStringShort());
            out.println(niv_recup.toStringShort());
            out.println("--------------------------");*/
            if (niv_test.getIdQualification() == niv_recup.getIdQualification() && niv_test.getIntituleQualification().equals(niv_recup.getIntituleQualification())) {
                out.println("[OK]Ajout et récup");
            } else {
                out.println("[ERROR]Ajout et recup");
            }

            out.println("[INFO]Test de la modification du niveau de qualification");
            methode = "Intitule Qualification";
            niv_recup.setIntituleQualification("Maitre de l'univers");
            niveauqualificationDAO.update(niv_recup);
            compare(niv_test.getIntituleQualification(), niv_recup.getIntituleQualification(), cl, methode, out);
            out.println();

            //Suppression du niveau de qualification
            ifosuppr(cl, out);
            niveauqualificationDAO.remove(niv_recup);
            methode = "suppression";
            testSupression(niveauqualificationDAO.findById(idTest), cl, methode, out);
            out.println();
        } catch (Exception e) {
            out.println("[ERROR]lors de la création/ajout/suppression d'un niveau de qualification");
            e.printStackTrace(out);
        }

        out.println("----------------------------------------------------------");
        out.println("[INFO]Test de la class SecteurActivite");
        cl = "Secteur Activite";
        SecteurActiviteDAO secteuractiviteDAO = null;
        try {
            secteuractiviteDAO = (SecteurActiviteDAO) ServicesLocator.getInstance().getRemoteInterface("SecteurActiviteDAO");
            out.println("[INFO]" + cl + "DAO créer avec succes");
        } catch (ServicesLocatorException ex) {
            out.println("[ERROR]lors de la cration du DAO secteur activite");
            throw new RuntimeException(ex);
        }


        out.println("[INFO]Liste des secteurs d'activité");
        List<SecteurActivite> secteuractivites = secteuractiviteDAO.findAll();
        for (SecteurActivite secteuractivite : secteuractivites) {
            secteuractivite.toStringShort();
        }
        out.println();
        try {
            out.println("[INFO]Secteur Activité n°1 : ");
            SecteurActivite sa = secteuractiviteDAO.findById(1);
            out.println("id:" + sa.getIdSecteur());
            out.println("intitule:" + sa.getIntituleActivite());
            out.println();

            out.println("[INFO]Secteur Activité n°2 : ");
            sa = secteuractiviteDAO.findById(2);
            out.println("id:" + sa.getIdSecteur());
            out.println("intitule:" + sa.getIntituleActivite());
            out.println();

            out.println("[INFO]Secteur Activité n°3 : ");
            sa = secteuractiviteDAO.findById(3);
            out.println("id:" + sa.getIdSecteur());
            out.println("intitule:" + sa.getIntituleActivite());
            out.println();
        } catch (Exception e) {
            out.println("[ERROR]Lors de la lecture des secteurs activite");
        }


        //Test de la création d'un secteur d'activité
        SecteurActivite sa_test = new SecteurActivite("joueur de flute, retrouvez nos meilleurs joueurs de pipo");
        SecteurActivite sa_recup = null;
        idTest = -1;
        try {
            sa_test = secteuractiviteDAO.persist(sa_test);
            idTest = sa_test.getIdSecteur();
            sa_recup = secteuractiviteDAO.findById(idTest);
            if (sa_test.getIdSecteur() == sa_recup.getIdSecteur() && sa_test.getIntituleActivite().equals(sa_recup.getIntituleActivite())) {
                out.println("[OK]Ajout et recup " + cl);
            } else {
                out.println("[ERROR]Ajout et recup " + cl);
            }

            out.println("[INFO] test de la modification de " + cl);
            sa_recup.setIntituleActivite("pipoteur pro");
            secteuractiviteDAO.update(sa_recup);
            sa_recup = secteuractiviteDAO.update(sa_recup);
            methode = "Intitulé Activité";
            compare(sa_test.getIntituleActivite(), sa_recup.getIntituleActivite(), cl, methode, out);

            out.println();
            ifosuppr(cl, out);
            secteuractiviteDAO.remove(sa_recup);
            testSupression(secteuractiviteDAO.findById(idTest), cl, "supprimer", out);
            try {
                out.println("----------------->test ajout en cascade");
                SecteurActivite s = secteuractiviteDAO.findById(Integer.parseInt("21"));
                CandidatDAO candidatDAO2 = (CandidatDAO) ServicesLocator.getInstance().getRemoteInterface("CandidatDAO");
                Candidat c = candidatDAO2.findById(5);
                s.getCandidats().add(c);
                secteuractiviteDAO.update(s);
                out.println("[OK]update du secteur");
                c.getSecteurActivites().add(s);
                candidatDAO2.update(c);
                out.println("[OK]update du cand");
                out.println("[OK]secteur cascade");
            } catch (ServicesLocatorException ex) {
                out.println("[ERROR] lors de l'ajout en cascade d'un secteur d'activité");
            }
        } catch (Exception e) {
            out.println("[ERROR]lors de la création/Ajout/Suppression d'un secteur activite");
        }


        //----------Test Candidat ---------
        out.println("Test du candidatDAO");
        CandidatDAO candidatDAO = null;
        try {
            candidatDAO = (CandidatDAO) ServicesLocator.getInstance().getRemoteInterface("CandidatDAO");
        } catch (ServicesLocatorException ex) {
            ex.printStackTrace();
        }
        out.println();

        out.println("Liste des candidats : ");
        List<Candidat> candidats = candidatDAO.findAll();
        for (Candidat candidat : candidats) {
            out.println(candidat.toStringShort());
        }
        out.println();
        Candidat c;
        try {
            out.println("Obtention de candidats n° 1 :");
            c = candidatDAO.findById(1);
            out.println("Id : " + c.getIdCandidat());
            out.println("Cv : " + c.getCv());
            out.println("Date de Depot : " + c.getDateDepot());
            out.println("Nom : " + c.getNom());
            out.println("Prenom : " + c.getPrenom());
            out.println("Adresse Mail : " + c.getAdresseEmail());
            out.println("Date de Naissance : " + c.getDateNaissance());
            out.println("Adresse Postale : " + c.getAdressePostale());
            out.println("Niveau Qualification : " + c.getNiveauQualification().getIntituleQualification());
            out.println();

            out.println("Obtention decandidat n° 2 :");
            c = candidatDAO.findById(2);
            out.println("Id : " + c.getIdCandidat());
            out.println("Cv : " + c.getCv());
            out.println("Date de Depot : " + c.getDateDepot());
            out.println("Nom : " + c.getNom());
            out.println("Prenom : " + c.getPrenom());
            out.println("Adresse Mail : " + c.getAdresseEmail());
            out.println("Date de Naissance : " + c.getDateNaissance());
            out.println("Adresse Postale : " + c.getAdressePostale());
            out.println("Niveau Qualification : " + c.getNiveauQualification().getIntituleQualification());
            out.println();

            out.println("Obtention de candidat n° 3 :");
            c = candidatDAO.findById(3);
            out.println("Id : " + c.getIdCandidat());
            out.println("Cv : " + c.getCv());
            out.println("Date de Depot : " + c.getDateDepot());
            out.println("Nom : " + c.getNom());
            out.println("Prenom : " + c.getPrenom());
            out.println("Adresse Mail : " + c.getAdresseEmail());
            out.println("Date de Naissance : " + c.getDateNaissance());
            out.println("Adresse Postale : " + c.getAdressePostale());
            out.println("Niveau Qualification : " + c.getNiveauQualification().getIntituleQualification());
            out.println();
        } catch (Exception e) {
            out.println("[ERROR]lors de la lecture de candidats");
        }

        out.println();


        try {

            Date datenaissance = new SimpleDateFormat("dd/MM/yyyy").parse("12/12/1212");
            Set<SecteurActivite> liste_secteurs = new HashSet<SecteurActivite>();
            //changer les Id
            liste_secteurs.add(secteuractiviteDAO.findById(19));
            liste_secteurs.add(secteuractiviteDAO.findById(25));
            Date datedepot = new SimpleDateFormat("dd/MM/yyyy").parse("11/11/1111");
            Candidat cand_test = new Candidat("Guyader", "Fabienne", "fabienne.guyader@imt-atlantique.fr", "PSF", "Incapable de diriger le pole stage et formation",
                    datedepot, datenaissance, niveauqualificationDAO.findById(1), liste_secteurs);
            Candidat cand_recup = null;
            idTest = -1;
            out.println("Ajout de la candidature de test");
            cand_test = candidatDAO.persist(cand_test);

            idTest = cand_test.getIdCandidat();
            cand_recup = candidatDAO.findById(idTest);
            /*out.println("--------------------------");
            out.println("Id candidat :"+idTest);
            out.println(cand_test.toStringShort());
            out.println(cand_recup.toStringShort());
            out.println("--------------------------");*/
            if ((cand_test.getIdCandidat() == cand_recup.getIdCandidat())
                    && (cand_test.getNom().equals(cand_recup.getNom()))
                    && (cand_test.getPrenom().equals(cand_recup.getPrenom()))
                    && (cand_test.getAdresseEmail().equals(cand_recup.getAdresseEmail()))
                    && (cand_test.getCv().equals(cand_recup.getCv()))
                    && (cand_test.getAdressePostale().equals(cand_recup.getAdressePostale()))
                    && (cand_test.getDateNaissance().equals(cand_recup.getDateNaissance()))
                    && (cand_test.getDateDepot().equals(cand_recup.getDateDepot()))
                    && (cand_test.getNiveauQualification().getIdQualification() == cand_recup.getNiveauQualification().getIdQualification())) {
                out.println("[OKAjout et Recup");
            } else {
                out.println("[ERROR]Ajout et Recup");
            }
            out.println();

            out.println("[INFO]Liste des Secteurs Activites de la candidature de test : ");
            Set<SecteurActivite> listes_activite_recup = cand_recup.getSecteurActivites();
            for (SecteurActivite secteurs_recup : listes_activite_recup) {
                out.println(secteurs_recup.getIntituleActivite());
            }
            out.println();


            out.println("[INFO]Modification de la candidature de test");
            cand_recup.setCv("FIP : Force d'intervention de la picole");
            candidatDAO.update(cand_recup);
            cand_recup = candidatDAO.findById(idTest);
            compare(cand_test.getCv(), cand_recup.getCv(), cl, "update CV", out);
            out.println();

            //Faire pour l'ensemble d'un candidat

            out.println("[INFO]Affichage par Secteur Activité et Niveau Qualif (Informatique et Bac+4) ");
            List<Candidat> list_test = candidatDAO.findBySecteurActiviteAndNiveauQualification(19, 4);
            for (Candidat candidat : list_test) {
                out.println(candidat.getNom() + " " + candidat.getPrenom());
            }
            out.println();


            //Utiliser l'outil de suppression
            out.println("Suppression du Candidat de test");
            methode = "suppression";
            ifosuppr(cl, out);
            candidatDAO.remove(cand_recup);
            testSupression(candidatDAO.findById(idTest), cl, methode, out);
            out.println();

            out.println("Liste des Candidats : ");
            candidats = candidatDAO.findAll();
            for (Candidat candidat : candidats) {
                out.println(candidat.getNom() + " " + candidat.getPrenom());
            }
            out.println();

        } catch (Exception e_ajout_1) {
            out.println("[ERROR]Lors de la création/ajout/suppression du candidat");
            e_ajout_1.printStackTrace();
        }


        //------------------------Offre Emploi-----------------------
        out.println("-----------------------------------------------------");
        out.println("--Offre emploi--");
        cl = "Offre emploi";
        OffreEmploiDAO offreemploiDAO = null;

        out.println("[INFO]Initialisation du DAO");
        try {
            offreemploiDAO = (OffreEmploiDAO) ServicesLocator.getInstance().getRemoteInterface("OffreEmploiDAO");
        } catch (ServicesLocatorException ex) {
            out.println("[ERROR]Echec de l'initialisation du DAO" + cl);
            throw new RuntimeException(ex);
        }
        out.println("[INFO]Liste des offre d'emplois : ");
        List<OffreEmploi> offreemplois = offreemploiDAO.findAll();
        for (OffreEmploi offreemploi : offreemplois) {
            out.println(offreemploi.toStringShort());

        }
        try {
            out.println();
            out.println("Obtention de l'offre n° 1 :");
            OffreEmploi of = offreemploiDAO.findById(1);
            out.println("Id : " + of.getIdOffre());
            out.println("Entreprise : " + of.getNoEntreprise());
            out.println("Descriptif Mission : " + of.getDescriptif());
            out.println("Profil Recherche : " + of.getProfilRecherche());
            out.println("Niveau Qualification : " + of.getNiveauQualification().getIntituleQualification());
            out.println("Date de Depot : " + of.getDateDepot());
            out.println();

            out.println("Obtention de l'offre n° 2 :");
            of = offreemploiDAO.findById(2);
            out.println("Id : " + of.getIdOffre());
            out.println("Entreprise : " + of.getNoEntreprise());
            out.println("Descriptif Mission : " + of.getDescriptif());
            out.println("Profil Recherche : " + of.getProfilRecherche());
            out.println("Niveau Qualification : " + of.getNiveauQualification().getIntituleQualification());
            out.println("Date de Depot : " + of.getDateDepot());
            out.println();

            out.println("Obtention de l'offre n° 3 :");
            of = offreemploiDAO.findById(3);
            out.println("Id : " + of.getIdOffre());
            out.println("Entreprise : " + of.getNoEntreprise());
            out.println("Descriptif Mission : " + of.getDescriptif());
            out.println("Profil Recherche : " + of.getProfilRecherche());
            out.println("Niveau Qualification : " + of.getNiveauQualification().getIntituleQualification());
            out.println("Date de Depot : " + of.getDateDepot());
            out.println();
        } catch (Exception e) {
            out.println("[ERROR]Lors de la lecture d'offre emplois");
        }


        try {
            cl = "offre Emploi";
            Date datedepot = new SimpleDateFormat("dd/MM/yyyy").parse("12/12/12");
            Set<SecteurActivite> liste_secteurs = new HashSet<SecteurActivite>();
            liste_secteurs.add(secteuractiviteDAO.findById(19));
            liste_secteurs.add(secteuractiviteDAO.findById(25));
            //OffreEmploi offre_test = new OffreEmploi(datedepot, "OFFRE DE FOUMALADE", "INGENIEUR TROP FORT",
            //      "HACKER LA NASA", entrepriseDAO.findById(2), niveauqualificationDAO.findById(4), liste_secteurs);
            OffreEmploi offre_test = new OffreEmploi("Ingénieur trop fort", "Hacker la NASA", "Bac+1000", niveauqualificationDAO.findById(1), datedepot, entrepriseDAO.findById(1),
                    liste_secteurs);
            OffreEmploi offre_recup = null;
            int id_oe = 0;
            out.println("Ajout de l'offreemploi de test");
            offre_test = offreemploiDAO.persist(offre_test);

            id_oe = offre_test.getIdOffre();
            offre_recup = offreemploiDAO.findById(id_oe);
            /*out.println("------------------------------");
            out.println("offre de test : ");
            out.println("ID:"+offre_test.getIdOffre());
            out.println("descriptif"+offre_test.getDescriptif());
            out.println("titre"+offre_test.getTitre());
            out.println("profile recherché"+offre_test.getProfilRecherche());
            */
            if ((offre_test.getIdOffre() == offre_recup.getIdOffre())
                    && (offre_test.getDescriptif().equals(offre_recup.getDescriptif()))
                    && (offre_test.getTitre().equals(offre_recup.getTitre()))
                    && (offre_test.getProfilRecherche().equals(offre_recup.getProfilRecherche()))
                    && (offre_test.getDateDepot().equals(offre_recup.getDateDepot()))
                    && (offre_test.getNoEntreprise().getId() == offre_recup.getNoEntreprise().getId())
                    && (offre_test.getNiveauQualification().getIdQualification() == offre_recup.getNiveauQualification().getIdQualification())) {
                out.println("[OK]Ajout et Recup");
            } else {
                out.println("[ERROR]Ajout et Recup");
            }
            out.println();

            out.println("[INFO]Liste des Secteurs Activites de l'offreemploi de test : ");
            Set<SecteurActivite> listes_activite_recup = offre_recup.getSecteurActivites();
            for (SecteurActivite secteurs_recup : listes_activite_recup) {
                out.println(secteurs_recup.getIntituleActivite());
            }
            out.println();

            out.println("[INFO]Liste des OffreEmplois : ");
            List<OffreEmploi> offresemplois = offreemploiDAO.findAll();
            for (OffreEmploi offreemploi : offresemplois) {
                out.println(offreemploi.getTitre());
            }
            out.println();

            out.println("[INFO]Modification de l'offreemploi de test");
            offre_recup.setTitre("HACKER LA DGSE");
            offreemploiDAO.update(offre_recup);
            methode = "changement Titre";
            offre_recup = offreemploiDAO.findById(id_oe);
            compare(offre_test.getTitre(), offre_recup.getTitre(), cl, methode, out);
            out.println();

            out.println("[INFO]Affichage par Secteur Activité et Niveau Qualif (Informatique et Bac+4) ");
            List<OffreEmploi> list_test = offreemploiDAO.findBySecteurActiviteAndNiveauQualification(1, 1);
            for (OffreEmploi offreemploi : list_test) {
                out.println(offreemploi.getTitre());
            }
            out.println();

            out.println("[INFO]Suppression de l'offreemploi de test");
            offreemploiDAO.remove(offre_recup);
            ifosuppr(cl, out);
            testSupression(offreemploiDAO.findById(id_oe), cl, "suppression offre emploi", out);
            out.println();

            out.println("[INFO]Liste des OffreEmplois : ");
            offresemplois = offreemploiDAO.findAll();
            for (OffreEmploi offreemploi : offresemplois) {
                out.println(offreemploi.getTitre());
            }
            out.println();
        } catch (Exception e3) {
            out.println("[ERROR]Lors de la création/modification/suppresion de l'offre emploi de test");
            e3.printStackTrace();
        }
        out.println();

        out.println("------------------Message Candidat------------------");

        //Message candidature
        MessageCandidatDAO messagecandidatDAO = null;
        try {
            messagecandidatDAO = (MessageCandidatDAO) ServicesLocator.getInstance().getRemoteInterface("MessageCandidatDAO");
        } catch (ServicesLocatorException e5) {
            e5.printStackTrace();
        }
        out.println("Contrôles de fonctionnement du DAO MessagecandidatDAO");
        out.println();

        try {
            // Contrôle(s) de fonctionnalités.
            out.println("[INFO]Liste des messagescandidat :");
            List<MessageCandidat> messagescandidatures = messagecandidatDAO.findAll();

            for (MessageCandidat messagecandidature : messagescandidatures) {
                out.println(messagecandidature.getCorpsMessage());
            }
            out.println();

            out.println("[INFO]Obtention du messagecandidat n° 1 :");
            MessageCandidat mc = messagecandidatDAO.findById(1);
            out.println("Id : " + mc.getIdMessageCandidat());
            out.println("Corps Message : " + mc.getCorpsMessage());
            out.println("Date d'Envoi : " + mc.getDateEnvoi());
            out.println("Cv : " + mc.getCandidat().getCv());
            out.println("Offre Emploi : " + mc.getOffreEmploi().getTitre());
            out.println();

            out.println("[INFO]Obtention du messagecandidat n° 2 :");
            mc = messagecandidatDAO.findById(2);
            out.println("Id : " + mc.getIdMessageCandidat());
            out.println("Corps Message : " + mc.getCorpsMessage());
            out.println("Date d'Envoi : " + mc.getDateEnvoi());
            out.println("Cv : " + mc.getCandidat().getCv());
            out.println("Offre Emploi : " + mc.getOffreEmploi().getTitre());
            out.println();

            out.println("[INFO]Obtention du messagecandidat n° 3 :");
            mc = messagecandidatDAO.findById(3);
            out.println("Id : " + mc.getIdMessageCandidat());
            out.println("Corps Message : " + mc.getCorpsMessage());
            out.println("Date d'Envoi : " + mc.getDateEnvoi());
            out.println("Cv : " + mc.getCandidat().getCv());
            out.println("Offre Emploi : " + mc.getOffreEmploi().getTitre());
            out.println();
        } catch (Exception e) {
            out.println("[ERROR]Lors de la lecture des messages candidature");
        }

        try {
            String s_envoi = "12/02/2021";
            Date dateenvoi = new SimpleDateFormat("dd/MM/yyyy").parse(s_envoi);
            //Messagecandidat mc_test = new Messagecandidat("Bonjour j'aime l'argent, embauchez moi.",dateenvoi, candidatDAO.findById(5), offreemploiDAO.findById(1));
            MessageCandidat mc_test = new MessageCandidat(candidatDAO.findById(1), offreemploiDAO.findById(1), "je vous aime putain", dateenvoi);
            MessageCandidat mc_recup = null;
            int id_mc = 0;
            out.println("Ajout du messagecandidature de test");
            mc_test = messagecandidatDAO.persist(mc_test);

            id_mc = mc_test.getIdMessageCandidat();
            mc_recup = messagecandidatDAO.findById(id_mc);
            if ((mc_test.getIdMessageCandidat() == mc_recup.getIdMessageCandidat())
                    && (mc_test.getCorpsMessage().equals(mc_recup.getCorpsMessage()))
                    && (mc_test.getDateEnvoi().equals(mc_recup.getDateEnvoi()))
                    && (mc_test.getCandidat().getIdCandidat() == mc_recup.getCandidat().getIdCandidat()
                    && (mc_test.getOffreEmploi().getIdOffre() == mc_recup.getOffreEmploi().getIdOffre()))) {
                out.println("[OK]Ajout et Recup");
            } else {
                out.println("[ERROR]Ajout et Recup");
            }

            out.println();

            out.println("Modification du messagecandidat de test");
            mc_recup.setCorpsMessage("Je suis jutste un FIP complètement bourré");
            messagecandidatDAO.update(mc_recup);

            mc_recup = messagecandidatDAO.findById(id_mc);

            //CHANGER ici pour recup les trucs qui vont vien avec la fonction compare

            if (mc_test.getCorpsMessage() != mc_recup.getCorpsMessage()) {
                out.println("[OK]Modif");
            } else {
                out.println("[ERROR]Modif");
            }
            out.println();

            out.println("[INFO]Suppression du messagecandidature de test");
            messagecandidatDAO.remove(mc_recup);
            //TEST du remove
            ifosuppr(cl, out);
            testSupression(messagecandidatDAO.findById(id_mc),cl, "suppression", out);
            out.println();


        } catch (Exception e_ajout_5) {
            // TODO Auto-generated catch block
            out.println("[ERROR]Lors de la création/ajout/mofif/suppression d'un message candidature");
            e_ajout_5.printStackTrace();
        }


        out.println("--------------------------------------------------------------");
        out.println("[INFO]Message Offre emploi");
        cl = "offre Emploi";

        MessageOffreemploiDAO messageoffreemploiDAO = null;
        try {
            messageoffreemploiDAO = (MessageOffreemploiDAO) ServicesLocator.getInstance().getRemoteInterface("MessageOffreemploiDAO");
        } catch (ServicesLocatorException ex) {
            throw new RuntimeException(ex);
        }
        List<MessageOffreemploi> messageoffreemplois = messageoffreemploiDAO.findAll();
        for (MessageOffreemploi messageoffreemploi : messageoffreemplois) {
            out.println(messageoffreemploi.getCorpsMessage());
        }


        try {
            // Contrôle(s) de fonctionnalités.

            out.println();

            out.println("Obtention du messageoffredemploi n° 1 :");
            MessageOffreemploi mod = messageoffreemploiDAO.findById(1);
            out.println("Id : " + mod.getIdMessageOffre());
            out.println("Corps Message : " + mod.getCorpsMessage());
            out.println("Date d'Envoi : " + mod.getDateEnvoi());
            out.println("Cv : " + mod.getCandidat().getCv());
            out.println("Offre Emploi : " + mod.getOffreEmploi().getTitre());
            out.println();

            out.println("Obtention du messageoffredemploi n° 2 :");
            mod = messageoffreemploiDAO.findById(2);
            out.println("Id : " + mod.getIdMessageOffre());
            out.println("Corps Message : " + mod.getCorpsMessage());
            out.println("Date d'Envoi : " + mod.getDateEnvoi());
            out.println("Cv : " + mod.getCandidat().getCv());
            out.println("Offre Emploi : " + mod.getOffreEmploi().getTitre());
            out.println();

            out.println("Obtention du messageoffredemploi n° 3 :");
            mod = messageoffreemploiDAO.findById(3);
            out.println("Id : " + mod.getIdMessageOffre());
            out.println("Corps Message : " + mod.getCorpsMessage());
            out.println("Date d'Envoi : " + mod.getDateEnvoi());
            out.println("Cv : " + mod.getCandidat().getCv());
            out.println("Offre Emploi : " + mod.getOffreEmploi().getTitre());
            out.println();
        } catch (Exception e) {
            out.println("[ERROR]Lors de la lecture des message Offre emploi");
        }

        try {
            String s_envoi = "06/02/2021";
            Date dateenvoi = new SimpleDateFormat("dd/MM/yyyy").parse(s_envoi);
            //Messageoffreemploi moe_test = new Messageoffreemploi("Bonjour voici une offre sympa pour vous",dateenvoi, candidatureDAO.findById(5), offreemploiDAO.findById(1));
            MessageOffreemploi moe_test = new MessageOffreemploi(candidatDAO.findById(1), offreemploiDAO.findById(1), "Bonjour, je suis juste la meilleur personnes", dateenvoi);
            MessageOffreemploi moe_recup = null;
            int id_moe = 0;
            out.println("[INFO]Ajout du messageoffredemploi de test");
            moe_test = messageoffreemploiDAO.persist(moe_test);

            id_moe = moe_test.getIdMessageOffre();
            moe_recup = messageoffreemploiDAO.findById(id_moe);
            if ((moe_test.getIdMessageOffre() == moe_recup.getIdMessageOffre())
                    && (moe_test.getCorpsMessage().equals(moe_recup.getCorpsMessage()))
                    && (moe_test.getDateEnvoi().equals(moe_recup.getDateEnvoi()))
                    && (moe_test.getCandidat().getIdCandidat() == moe_recup.getCandidat().getIdCandidat()
                    && (moe_test.getOffreEmploi().getIdOffre() == moe_recup.getOffreEmploi().getIdOffre()))) {
                out.println("[OK]Ajout et Recup");
            } else {
                out.println("ERROR]Ajout et Recup");
            }
            out.println();

            out.println();

            out.println("Modification du messageoffredemploi de test");
            moe_recup.setCorpsMessage("REPONDEZ VITE A L'OFFRE !!!");
            messageoffreemploiDAO.update(moe_recup);

            moe_recup = messageoffreemploiDAO.findById(id_moe);
            if (moe_test.getCorpsMessage() != moe_recup.getCorpsMessage()) {
                out.println("Modif OK");
                out.println("Ancien Corps Message : " + moe_test.getCorpsMessage());
                out.println("Nouveau Corps Message : " + moe_recup.getCorpsMessage());
            } else {
                out.println("Modif KO");
                out.println("Ancien Corps Message : " + moe_test.getCorpsMessage());
                out.println("Nouveau Corps Message : " + moe_recup.getCorpsMessage());
            }
            out.println();

            out.println("Suppression du messageoffredemploi de test");
            messageoffreemploiDAO.remove(moe_recup);

            if (messageoffreemploiDAO.findById(id_moe) == null) {
                out.println("Suppression OK");
            } else {
                out.println("Suppression KO");
            }
            out.println();

        } catch (Exception e_ajout_6) {
            // TODO Auto-generated catch block
            out.println("[ERROR]Lors de l'ajout/modif/suppression d'un message offre emploi");
            e_ajout_6.printStackTrace();
        }

        out.println("-----------------------------------------------------------------------------");


        //Fin du void service
    }

    private void compare(String chaine1, String chaine2, String classe, String methode, PrintWriter out) {
        if (chaine1 != chaine2) {
            out.println("[OK]" + classe + " " + methode);
        } else {
            out.println("[ERROR]" + classe + " " + methode);
        }
    }

    public void ifosuppr(String cl, PrintWriter out) {
        out.println("[INFO]Suppression de " + cl);
    }

    private void testSupression(Object entiteSuppr, String cl, String methode, PrintWriter out) {
        if (entiteSuppr == null) {
            out.println("[OK]" + cl + " " + methode);
        } else {
            out.println("[ERROR]" + cl + " " + methode);
        }
    }

    private LinkedList<Entreprise> generateurEntreprise(EntrepriseDAO entrepriseDAO, PrintWriter out) {
        out.println();
        out.println("[INFO]Création entreprise de références");
        LinkedList<Entreprise> entrepriseList = new LinkedList<Entreprise>();
        Entreprise entreprise;
        try {
            for (int i = 1; i <= 10; i++) {
                entreprise = new Entreprise("Adresse" + i, "Descriptif" + i, "Nom" + i);
                entreprise = entrepriseDAO.persist(entreprise);
                entrepriseList.add(entreprise);
            }
        } catch (Exception e) {
            out.println("[ERROR]Problème lors de la création des entreprises de référence");
        }
        return entrepriseList;
    }

    private void cleanEntreprise(LinkedList<Entreprise> entrepriseList, PrintWriter out, EntrepriseDAO entrepriseDAO){
        out.println("[INFO]Clean entreprise :");
        for (Entreprise entreprise : entrepriseList){
            try{
                entrepriseDAO.remove(entreprise);
            }catch (Exception e){
                out.println("[ERROR]Lors de la suppression des entreprises : "+entreprise.toString());
            }
        }
        out.println("[OK]Entrepise supprimées");
    }

    //-------------PARTIE LAURE-----------------
    private LinkedList<OffreEmploi> generateurOffre(OffreEmploiDAO offreEmploiDAO, PrintWriter out){
        out.println();
        out.println("[INFO]Création d'offres emploi de références");
        LinkedList<OffreEmploi> offreEmploiList = new LinkedList<OffreEmploi>();
        OffreEmploi offreEmploi;
        try {
            for (int i = 1; i <= 10; i++) {
                offreEmploi = new OffreEmploi("Adresse" + i, "Descriptif" + i, "Nom" + i);
                offreEmploi = offreEmploiDAO.persist(offreEmploi);
                offreEmploiList.add(offreEmploi);
            }
        } catch (Exception e) {
            out.println("[ERROR]Problème lors de la création d'offres emploi de référence");
        }
        return offreEmploiList;
    }



}