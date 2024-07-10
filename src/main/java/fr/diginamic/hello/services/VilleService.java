package fr.diginamic.hello.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.diginamic.hello.dao.VilleDao;
import fr.diginamic.hello.entites.Departement;
import fr.diginamic.hello.entites.VilleTP6;
import fr.diginamic.hello.repositories.DepartementRepository;
import fr.diginamic.hello.repositories.VilleRepository;
import jakarta.annotation.PostConstruct;

@Service
public class VilleService {

	@Autowired
	private VilleDao villeDao;

	@Autowired
	private VilleRepository villeRepo;

	@Autowired
	private DepartementRepository departementRepo;

	List<VilleTP6> listeVilles;

	@PostConstruct
	public void initDonnees() {
		this.listeVilles = extractVilleTP6s();
	}

	public List<VilleTP6> extractVilleTP6s() {
		return villeDao.extractVilles();
	}

	public VilleTP6 extractVilleTP6Id(int idVilleTP6) {

		VilleTP6 villeTP6ParId = listeVilles.stream().filter(v -> v.getId() == idVilleTP6).findFirst().orElse(null);
		return villeTP6ParId;
	}

	public VilleTP6 extractVilleTP6Nom(String nom) {
		VilleTP6 villeTP6ParNom = listeVilles.stream().filter(v -> v.getNom().equalsIgnoreCase(nom)).findFirst()
				.orElse(null);
		return villeTP6ParNom;
	}

	public void insertVille(VilleTP6 villeTP6) {

		VilleTP6 villeTP6Existante = extractVilleTP6Nom(villeTP6.getNom());

		if (villeTP6Existante == null) {
//			departementService.insertDepartement(villeTP6.getDepartement());
			Departement departement = villeTP6.getDepartement();
			Departement departementExistant = departementRepo.findByCodeDept(departement.getCodeDept());
			if (departementExistant == null) {
				departementRepo.save(departement);
			} else {
				villeTP6.setDepartement(departementExistant);
			}
			villeRepo.save(villeTP6);
			listeVilles.add(villeTP6);

		}
	}

	public List<VilleTP6> modifierVilleTP6(int idVilleTP6, VilleTP6 villeTP6Modifiee) {

		VilleTP6 villeAModifier = villeRepo.findById(idVilleTP6)
				.orElseThrow(() -> new RuntimeException("Ville not found"));
		if (villeAModifier != null) {
			System.out.println("Ville trouvée : " + villeAModifier);
			villeAModifier.setNom(villeTP6Modifiee.getNom());
			villeAModifier.setNbHabitants(villeTP6Modifiee.getNbHabitants());
//			villeDao.update(villeTP6Modifiee); // n'est même pas nécessaire
			System.out.println("Ville modifiée : " + villeTP6Modifiee);
		} else {
			System.out.println("VilleService : Ville avec id " + idVilleTP6 + " non trouvée.");
		}
		return listeVilles;
	}

	public List<VilleTP6> supprimerVilleTP6(int idVilleTP6) {
		VilleTP6 villeTP6ParId = listeVilles.stream().filter(v -> v.getId() == idVilleTP6).findFirst().orElse(null);
		if (villeTP6ParId != null) {
			listeVilles.remove(villeTP6ParId);
			villeDao.delete(villeTP6ParId);
			System.out.println(listeVilles);
		}
		return listeVilles;
	}

}
