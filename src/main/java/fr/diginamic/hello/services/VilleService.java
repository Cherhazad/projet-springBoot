package fr.diginamic.hello.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import fr.diginamic.hello.entites.Ville;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Service
public class VilleService {

	@PersistenceContext
	private EntityManager em;
	List<Ville> listeVilles = new ArrayList<>();

	@GetMapping // TODO Annotations à mettre dans la classe VilleController sur l'appel des méthodes ?
	public List<Ville> extractVilles() {
		TypedQuery<Ville> query = em.createQuery("SELECT v FROM Ville v", Ville.class);
		listeVilles = new ArrayList<>(query.getResultList());
		return listeVilles;
	}

	@GetMapping("/{idVille}")
	public Ville extractVilleId(@PathVariable int idVille, @RequestBody Ville nvVille) {

		Ville villeParId = listeVilles.stream().filter(ville -> ville.getId() == idVille).findFirst().orElse(null);
		return villeParId;
	}

	@GetMapping("/{nom}")
	public Ville extractVilleNom(@PathVariable String nom, @RequestBody Ville nvVille) {
		Ville villeParNom = listeVilles.stream().filter(ville -> ville.getNom().equalsIgnoreCase(nom)).findFirst()
				.orElse(null);
		return villeParNom;
	}

	@PostMapping("/insertVille")
	public List<Ville> insertVille(@RequestBody Ville ville) {

		Optional<Ville> villeExistante = listeVilles.stream().filter(v -> v.getNom().equals(ville.getNom()))
				.findFirst();
		if (villeExistante == null) {
			listeVilles.add(ville);
			ResponseEntity.ok("Ville insérée avec succès");
		} else {
			ResponseEntity.badRequest().body("La ville existe déjà");
		}
		return listeVilles;

	}

	@PutMapping("/{idVille}")
	public List<Ville> modifierVille(@PathVariable int idVille, @RequestBody Ville villeModifiee) {

		if (listeVilles.stream().filter(v -> v.getId() == idVille) != null) {
			listeVilles.add(villeModifiee);
		}
		return listeVilles;
	}

	@DeleteMapping("/{idVille}")
	public List<Ville> supprimerVille(@PathVariable int idVille) {
		Ville villeParId = listeVilles.stream().filter(ville -> ville.getId() == idVille).findFirst().orElse(null);
		if (villeParId != null) {
			listeVilles.remove(villeParId);
		}
		return listeVilles;
	}

}
