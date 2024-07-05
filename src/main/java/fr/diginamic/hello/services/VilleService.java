package fr.diginamic.hello.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import fr.diginamic.hello.DAO.VilleDao;
import fr.diginamic.hello.entites.VilleTP6;
import jakarta.annotation.PostConstruct;

@Service
public class VilleService {
	
	@Autowired
	private VilleDao villeDao;
	
	List<VilleTP6> listeVilles;
	
	
	@PostConstruct
	public void initDonnees() {
		this.listeVilles = extractVilleTP6s();
	}

	
	public List<VilleTP6> extractVilleTP6s() {
		return villeDao.extractVilles();
	}


	public VilleTP6 extractVilleTP6Id(int idVilleTP6) {

		VilleTP6 villeTP6ParId = listeVilles.stream().filter(v -> v.getId() == idVilleTP6).findFirst()
				.orElse(null);
		return villeTP6ParId;
	}

	
	public VilleTP6 extractVilleTP6Nom(String nom) {
		VilleTP6 villeTP6ParNom = listeVilles.stream().filter(v -> v.getNom().equalsIgnoreCase(nom))
				.findFirst().orElse(null);
		return villeTP6ParNom;
	}

	
	public List<VilleTP6> insertVille(VilleTP6 villeTP6) {

		VilleTP6 villeTP6Existante = listeVilles.stream().filter(v -> v.getNom().equals(villeTP6.getNom()))
				.findFirst().orElse(null);
		if (villeTP6Existante == null) {
			listeVilles.add(villeTP6);
			villeDao.insert(villeTP6);
			System.out.println(listeVilles);
//			ResponseEntity.ok("VilleTP6 insérée avec succès");
		} 
//			else {
//			ResponseEntity.badRequest().body("La VilleTP6 " + villeTP6 + " existe déjà");
//		}
		return listeVilles; //faire un return de ResponseEntity.ok(listeVilles.toString("\n") à revoir pour afficher à la ligne les villes.
	}

	public List<VilleTP6> modifierVilleTP6(int idVilleTP6, VilleTP6 villeTP6Modifiee) {

		VilleTP6 villeAModifier = listeVilles.stream().filter(v -> v.getId() == idVilleTP6).findFirst().orElse(null);
		if (villeAModifier != null) {
			System.out.println("Ville trouvée : " + villeAModifier);
			villeAModifier.setNom(villeTP6Modifiee.getNom());
			villeAModifier.setNbHabitants(villeTP6Modifiee.getNbHabitants());
			villeDao.update(villeTP6Modifiee);
			System.out.println("Ville modifiée : " + villeTP6Modifiee);
			System.out.println(listeVilles);
		} else {
            System.out.println("VilleService : Ville avec id " + idVilleTP6 + " non trouvée.");
        }
		return listeVilles;
	}


	public List<VilleTP6> supprimerVilleTP6(int idVilleTP6) {
		VilleTP6 villeTP6ParId = listeVilles.stream().filter(v -> v.getId() == idVilleTP6).findFirst()
				.orElse(null);
		if (villeTP6ParId != null) {
			listeVilles.remove(villeTP6ParId);
			villeDao.delete(villeTP6ParId);
			System.out.println(listeVilles);
		}
		return listeVilles;
	}

}
