package fr.diginamic.hello.controleurs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.hello.entites.VilleTP6;
import fr.diginamic.hello.services.VilleService;

@RestController
@RequestMapping("/ville")
public class VilleControllerTP6 {

	@Autowired
	private VilleService villeService;
	
	@GetMapping
	public List<VilleTP6> extraireVilles(){
		return villeService.extractVilleTP6s();
	}
	
	@GetMapping("/parId/{id}")
	public VilleTP6 extraireVilleParId(@PathVariable int id) {
		return villeService.extractVilleTP6Id(id);
	}
	
	@GetMapping("/parNom/{nom}")
	public VilleTP6 extractVilleTP6Nom(@PathVariable String nom) {
		return villeService.extractVilleTP6Nom(nom);
	}
	
	@PostMapping
	public List<VilleTP6> insertVille(@RequestBody VilleTP6 VilleTP6) {
		return villeService.insertVille(VilleTP6);
	}
	
	@PutMapping("/{id}")
	public List<VilleTP6> updateVille(@PathVariable int id, @RequestBody VilleTP6 villeTP6) {
		System.out.println("Appel à updateVille avec id: " + id + " et ville: " + villeTP6);
		return villeService.modifierVilleTP6(id, villeTP6);
	}
	
	@DeleteMapping("/{id}")
	public List<VilleTP6> deleteVille(@PathVariable int id) {
		return villeService.supprimerVilleTP6(id);
	}
	
}
