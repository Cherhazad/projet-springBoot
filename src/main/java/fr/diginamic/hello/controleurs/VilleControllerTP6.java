package fr.diginamic.hello.controleurs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/Ville")
public class VilleControllerTP6 {

	@Autowired
	private VilleService villeService;
	
	@GetMapping
	public List<VilleTP6> extraireVilles(){
		return villeService.extractVilleTP6s();
	}
	
//	@GetMapping("/{id}")
//	public Ville extraireVilleParId() {
//		return villeService.extractVilles(0, null)
//	}
	
	@PostMapping
	public List<VilleTP6> insertVille(@RequestBody VilleTP6 VilleTP6) {
		return villeService.insertVilleTP6(VilleTP6);
	}
	
	@PutMapping("/{id}")
	public List<VilleTP6> updateVille(@PathVariable int id, @RequestBody VilleTP6 VilleTP6) {
		return villeService.modifierVilleTP6(id, VilleTP6);
	}
	
}
