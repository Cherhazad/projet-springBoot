package fr.diginamic.hello.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import fr.diginamic.hello.DAO.VilleDao;
import fr.diginamic.hello.entites.Ville;
import fr.diginamic.hello.entites.VilleTP6;

@Service
public class VilleService {
	
	@Autowired
	private VilleDao villeDao;
	
	List<VilleTP6> listeVilles = new ArrayList<>();
	
	
	// @PostConstruct
	public void initDonnees() {
		this.listeVilles = extractVilleTP6s();
	}

	
	public List<VilleTP6> extractVilleTP6s() {
		return villeDao.extractVilles();
	}


	public VilleTP6 extractVilleTP6Id(int idVilleTP6, VilleTP6 nvVilleTP6) {

		VilleTP6 VilleTP6ParId = listeVilles.stream().filter(VilleTP6 -> VilleTP6.getId() == idVilleTP6).findFirst()
				.orElse(null);
		return VilleTP6ParId;
	}

	//@GetMapping("/{nom}")
	public VilleTP6 extractVilleTP6Nom(String nom, VilleTP6 nvVilleTP6) {
		VilleTP6 VilleTP6ParNom = listeVilles.stream().filter(VilleTP6 -> VilleTP6.getNom().equalsIgnoreCase(nom))
				.findFirst().orElse(null);
		return VilleTP6ParNom;
	}

	//@PostMapping("/insertVilleTP6")
	public List<VilleTP6> insertVilleTP6(VilleTP6 VilleTP6) {

		VilleTP6 VilleTP6Existante = listeVilles.stream().filter(v -> v.getNom().equals(VilleTP6.getNom()))
				.findFirst().orElse(null);
		if (VilleTP6Existante == null) {
			listeVilles.add(VilleTP6);
			villeDao.insert(VilleTP6);
			System.out.println(listeVilles);
			ResponseEntity.ok("VilleTP6 insérée avec succès");
		} else {
			ResponseEntity.badRequest().body("La VilleTP6 " + VilleTP6 + " existe déjà");
		}
		return listeVilles;

	}

	public List<VilleTP6> modifierVilleTP6(int idVilleTP6, VilleTP6 VilleTP6Modifiee) {

		VilleTP6 villeAModifier = listeVilles.stream().filter(v -> v.getId() == idVilleTP6).findFirst().orElse(null);
		if (villeAModifier != null) {
//			villeAModifier.setId(VilleTP6Modifiee.getId());
			villeAModifier.setNom(VilleTP6Modifiee.getNom());
			villeAModifier.setNbHabitants(VilleTP6Modifiee.getNbHabitants());
			villeDao.update(VilleTP6Modifiee);
			System.out.println(listeVilles);
		}
		return listeVilles;
	}

	//@DeleteMapping("/{idVilleTP6}")
	public List<VilleTP6> supprimerVilleTP6(int idVilleTP6) {
		VilleTP6 VilleTP6ParId = listeVilles.stream().filter(VilleTP6 -> VilleTP6.getId() == idVilleTP6).findFirst()
				.orElse(null);
		if (VilleTP6ParId != null) {
			listeVilles.remove(VilleTP6ParId);
			System.out.println(listeVilles);
		}
		return listeVilles;
	}

}
