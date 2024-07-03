package fr.diginamic.hello.controleurs;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.hello.entites.Ville;

@RestController
@RequestMapping("/villes")
public class VilleController {

	List<Ville> listeVilles = new ArrayList<>(); //utiliser List.of return une erreur null car une liste est immutable.

	public VilleController() {
		listeVilles.add(new Ville("Montpellier", 36000));
		listeVilles.add(new Ville("Paris", 85000));
	}

	@GetMapping
	public List<Ville> listerVilles() {
		return listeVilles;
	}

	@PostMapping("/insertVille")
	public ResponseEntity<String> insertVille(@RequestBody Ville nvVille) {

		for (Ville ville : listeVilles) {
			if (ville.getNom().equals(nvVille.getNom())) {

				return ResponseEntity.badRequest().body("La ville existe déjà");
			}
		}
		listeVilles.add(nvVille);
		return ResponseEntity.ok("Ville insérée avec succès");
	}

}
