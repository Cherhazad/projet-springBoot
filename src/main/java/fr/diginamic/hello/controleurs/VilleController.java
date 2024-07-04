package fr.diginamic.hello.controleurs;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.hello.entites.Ville;

@RestController
@RequestMapping("/villes")
public class VilleController {

	List<Ville> listeVilles = new ArrayList<>(); //utiliser List.of return une erreur null car une liste est immutable.

	public VilleController() {
		listeVilles.add(new Ville(1, "Montpellier", 36000));
		listeVilles.add(new Ville(2, "Paris", 85000));
	}
	
	// méthodes GET

	@GetMapping
	public List<Ville> listerVilles() {
		return listeVilles;
	}

	@GetMapping(path = "/toutesVilles/{id}")
	public Ville trouverVilleParId(@PathVariable int id) {
		for (Ville ville : listeVilles) {
			if (ville.getId() == id) {
				return ville;
			}
		}
		return null;
	}
	
	// méthodes POST
	
	@PostMapping // ("/insertVille") pas besoin de mettre ce complément d'url si on n'a qu'une seule fois le post.
	public ResponseEntity<String> insertVille(@RequestBody Ville nvVille) {

//		for (Ville ville : listeVilles) {
//			if (ville.getNom().equals(nvVille.getNom()) || ville.getId() == nvVille.getId()) {
//
//				return ResponseEntity.badRequest().body("La ville existe déjà");
//			}
//			
//		}
//		listeVilles.add(nvVille);
//		return ResponseEntity.ok("Ville insérée avec succès");
		
		if (listeVilles.contains(nvVille)) { // il faut alors redéfinir la méthode equals dans la classe Ville (voir corrigé obsidian).
			return ResponseEntity.badRequest().body("La ville " + nvVille.getNom() + " existe déjà.");
		}
		listeVilles.add(nvVille);
		return ResponseEntity.ok("Ville insérée avec succès.");
		
	}
	
	
	
	// méthodes PUT
	
	@PutMapping("/modifierVille/{id}")
	public Ville modifierVille(@PathVariable int id, @RequestBody Ville nvVille) {
		for (Ville ville : listeVilles) {
			if(ville.getId() == id) {
				return nvVille;
			}
		}
		return null;
	}
	
	// méthodes DELETE
	
	@DeleteMapping("/supprimerVille/{id}")
	public Ville supprimerVille(@PathVariable int id, @RequestBody Ville nvVille) {
		for (Ville ville : listeVilles) {
			if(ville.getId() == id) {
				return nvVille;
			}
		}
		return null;
	}
	
}
