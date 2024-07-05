package fr.diginamic.hello.controleurs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.hello.entites.Departement;

@RestController
@RequestMapping("/departement")
public class DepartementController {
	
	@Autowired
	private DepartementService depService;
	
	@GetMapping
	public List<Departement> extraireDepartement(){
		return depService.extractDepartements();
	}
	
	@GetMapping("/parCodeDep/{codeDep}")
	public Departement extraireDepParId(@PathVariable String codeDep) {
		return depService.extractDeptId(codeDep);
	}

	@GetMapping("/parNom/{nom}")
	public Departement extractDepNom(@PathVariable String nom) {
		return depService.extractDepNom(nom);
	}
	
	@PostMapping
	public List<Departement> insertDepartement(@RequestBody Departement dept) {
		return depService.insertDepertement(dept);
	}
	
	@PutMapping("/{codeDep}")
	public List<Departement> updateVille(@PathVariable String codeDep, @RequestBody Departement dept) {
		System.out.println("Appel à updateDepartement avec codeDep: " + codeDep + " et departement: " + dept);
		return depService.modifierDepartement(codeDep, dept);
	}
	
	@DeleteMapping("/{codeDep}")
	public List<Departement> deleteDepartement(@PathVariable String codeDep) {
		return depService.supprimerDepartement(codeDep);
	}
	
	
	
}
