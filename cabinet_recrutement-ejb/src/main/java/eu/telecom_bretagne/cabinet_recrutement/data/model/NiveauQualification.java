package eu.telecom_bretagne.cabinet_recrutement.data.model;
// Generated Feb 2, 2023, 2:41:08 PM by Hibernate Tools 5.4.20.Final


import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.*;

/**
 * NiveauQualification generated by hbm2java
 */
@Entity
@Table(name="niveau_qualification"
    ,schema="public"
)
public class NiveauQualification  implements java.io.Serializable {


     private int idQualification;
     private String intituleQualification;
     private Set<Candidat> candidats = new HashSet<Candidat>(0);
     private Set<OffreEmploi> offreEmplois = new HashSet<OffreEmploi>(0);

    public NiveauQualification() {
    }

	
    public NiveauQualification(int idQualification) {
        this.idQualification = idQualification;
    }
    public NiveauQualification(int idQualification, String intituleQualification, Set<Candidat> candidats, Set<OffreEmploi> offreEmplois) {
       this.idQualification = idQualification;
       this.intituleQualification = intituleQualification;
       this.candidats = candidats;
       this.offreEmplois = offreEmplois;
    }

    public NiveauQualification(String intituleQualification, Set<Candidat> candidats, Set<OffreEmploi> offreEmplois) {
        this.intituleQualification = intituleQualification;
        this.candidats = candidats;
        this.offreEmplois = offreEmplois;
    }

    public NiveauQualification(String intituleQualification){
        this.intituleQualification = intituleQualification;
    }
   
     @Id 

    
    @Column(name="id_qualification", unique=true, nullable=false)
     @SequenceGenerator(name="NIVEAU_QUALIFICATION_ID_GENERATOR", sequenceName="NIVEAU_QUALIFICATION_ID_SEQ", allocationSize=1)
     @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="NIVEAU_QUALIFICATION_ID_GENERATOR")
    public int getIdQualification() {
        return this.idQualification;
    }
    
    public void setIdQualification(int idQualification) {
        this.idQualification = idQualification;
    }

    
    @Column(name="intitule_qualification")
    public String getIntituleQualification() {
        return this.intituleQualification;
    }
    
    public void setIntituleQualification(String intituleQualification) {
        this.intituleQualification = intituleQualification;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="niveauQualification")
    public Set<Candidat> getCandidats() {
        return this.candidats;
    }
    
    public void setCandidats(Set<Candidat> candidats) {
        this.candidats = candidats;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="niveauQualification")
    public Set<OffreEmploi> getOffreEmplois() {
        return this.offreEmplois;
    }
    
    public void setOffreEmplois(Set<OffreEmploi> offreEmplois) {
        this.offreEmplois = offreEmplois;
    }


    public String toStringShort(){
        return "NiveauQualification[idQualification="+this.idQualification+", IntituleQualification="+this.intituleQualification+"]";
    }

    public Candidat removeCandidat(Candidat candidature) {
        getCandidats().remove(candidature);
        candidature.setNiveauQualification(null);

        return candidature;
    }



}

