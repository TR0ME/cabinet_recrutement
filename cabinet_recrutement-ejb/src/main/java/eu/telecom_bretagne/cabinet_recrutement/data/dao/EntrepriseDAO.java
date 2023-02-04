package eu.telecom_bretagne.cabinet_recrutement.data.dao;

import java.util.List;

// import javax.ejb.LocalBean;
// import javax.ejb.Stateless;
// import javax.persistence.EntityManager;
// import javax.persistence.PersistenceContext;
// import javax.persistence.Query;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import eu.telecom_bretagne.cabinet_recrutement.data.model.Entreprise;

/**
 * Session Bean implementation class EntrepriseDAO
 * @author Philippe TANGUY
 */
@Stateless
@LocalBean
public class EntrepriseDAO
{
  //-----------------------------------------------------------------------------
  /**
   * Référence vers le gestionnaire de persistance.
   */
  @PersistenceContext
  EntityManager entityManager;
  //-----------------------------------------------------------------------------
  /**
   * Default constructor.
   */
  public EntrepriseDAO()
  {
    // TODO Auto-generated constructor stub
  }
  //-----------------------------------------------------------------------------
  public Entreprise findById(Integer id)
  {
    return entityManager.find(Entreprise.class, id);
  }
  //----------------------------------------------------------------------------
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public List<Entreprise> findAll()
  {
    Query query = entityManager.createQuery("select entreprise from Entreprise entreprise order by entreprise.id");
    List l = query.getResultList(); 
    
    return (List<Entreprise>)l;
  }
  //-----------------------------------------------------------------------------
  public void remove(Entreprise entreprise)
  {
    if(!entityManager.contains(entreprise)) // Si l'entité n'est pas dans un état
                                            // "géré" (managed), il est impossible
                                            // de la supprimer directement, erreur
    {                                       // "Entity must be managed to call
                                            // remove"
      entreprise = entityManager.merge(entreprise); // Il faut la "rattacher" au
                                                    // contexte de persistance
                                                    // par l'appel de la méthode
                                                    // merge de l'Entity Manager.
    }
    entityManager.remove(entreprise);  // L'entité était déjà attachée ou a été
                                       // rattachée, on peut donc la supprimer...
  }

  // -----------------------------------------------------------------------------
  public Entreprise persist(Entreprise Entreprise) {
    if (Entreprise != null) {
      entityManager.persist(Entreprise);
    }
    return Entreprise;
  }

  // -----------------------------------------------------------------------------
  public Entreprise update(Entreprise Entreprise) {
    if (Entreprise != null) {
      entityManager.merge(Entreprise);
    }
    return Entreprise;
  }


}
