package eu.telecom_bretagne.cabinet_recrutement.data.model;
// Generated Feb 2, 2023, 2:41:08 PM by Hibernate Tools 5.4.20.Final


import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.*;

/**
 * SecteurActivite generated by hbm2java
 */
@Entity
@Table(name="secteur_activite"
    ,schema="public"
)
public class SecteurActivite  implements java.io.Serializable {


     private int idSecteur;
     private String intituleActivite;
     private Set<Candidat> candidats = new HashSet<Candidat>(0);
     private Set<OffreEmploi> offreEmplois = new HashSet<OffreEmploi>(0);

    public SecteurActivite() {
    }

	
    public SecteurActivite(int idSecteur) {
        this.idSecteur = idSecteur;
    }
    public SecteurActivite(int idSecteur, String intituleActivite, Set<Candidat> candidats, Set<OffreEmploi> offreEmplois) {
       this.idSecteur = idSecteur;
       this.intituleActivite = intituleActivite;
       this.candidats = candidats;
       this.offreEmplois = offreEmplois;
    }

    public SecteurActivite(String intituleActivite) {
        this.intituleActivite = intituleActivite;
    }
   
     @Id
    @Column(name="id_secteur", unique=true, nullable=false)
     @SequenceGenerator(name="SECTEUR_ACTIVITE_ID_GENERATOR", sequenceName="SECTEUR_ACTIVITE_ID_SEQ", allocationSize=1)
     @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SECTEUR_ACTIVITE_ID_GENERATOR")
    public int getIdSecteur() {
        return this.idSecteur;
    }
    
    public void setIdSecteur(int idSecteur) {
        this.idSecteur = idSecteur;
    }

    
    @Column(name="intitule_activite")
    public String getIntituleActivite() {
        return this.intituleActivite;
    }
    
    public void setIntituleActivite(String intituleActivite) {
        this.intituleActivite = intituleActivite;
    }

@ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name="secteur_candidat", schema="public", joinColumns = { 
        @JoinColumn(name="no_secteur", nullable=false, updatable=false) }, inverseJoinColumns = { 
        @JoinColumn(name="no_candidat", nullable=false, updatable=false) })
    public Set<Candidat> getCandidats() {
        return this.candidats;
    }
    
    public void setCandidats(Set<Candidat> candidats) {
        this.candidats = candidats;
    }

    public void addCandidat(Candidat candidat){
        this.candidats.add(candidat);
    }

@ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name="secteur_offre", schema="public", joinColumns = { 
        @JoinColumn(name="no_secteur", nullable=false, updatable=false) }, inverseJoinColumns = { 
        @JoinColumn(name="no_offre", nullable=false, updatable=false) })
    public Set<OffreEmploi> getOffreEmplois() {
        return this.offreEmplois;
    }
    
    public void setOffreEmplois(Set<OffreEmploi> offreEmplois) {
        this.offreEmplois = offreEmplois;
    }


    public String toStringShort(){
        return "SecteurActivite[idSecteur="+this.idSecteur+", intitule="+this.intituleActivite+"]";
    }


}

