package fr.diginamic.hello.controleurs;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.hello.dto.VilleTP6Dto;
import fr.diginamic.hello.entites.VilleTP6;
import fr.diginamic.hello.repositories.VilleRepository;
import fr.diginamic.hello.services.VilleMapper;
import fr.diginamic.hello.services.VilleService;

@RestController
@RequestMapping("/ville")
public class VilleControllerRepo {

	@Autowired
	private VilleRepository villeRepository;

	@Autowired
	private VilleService villeService;

	@GetMapping
	public List<VilleTP6Dto> extraireVilles() {
		return villeRepository.findAll().stream().map(VilleMapper::toDto).collect(Collectors.toList());
	}

	@GetMapping("/parId/{id}")
	public VilleTP6Dto extraireVilleParId(@PathVariable int id) {
		VilleTP6 ville = villeRepository.findById(id);
		VilleTP6Dto villeDto = VilleMapper.toDto(ville);
		return villeDto;
	}

	@GetMapping("/parNom/{nom}")
	public VilleTP6Dto extractVilleTP6Nom(@PathVariable String nom) {
		VilleTP6 ville = villeRepository.findByNom(nom);
		VilleTP6Dto villeDto = VilleMapper.toDto(ville);
		return villeDto;

	}

	@PostMapping
	public VilleTP6 insertVille(@RequestBody VilleTP6 villeTP6) {
		return villeRepository.save(villeTP6);
	}
//	public VilleTP6 insertVille(@RequestBody VilleTP6 villeTP6) {
//		return villeRepository.save(villeTP6, villeTP6.getDepartement());
//	}

	// villeService ici
	@PutMapping("/{id}")
	public List<VilleTP6> updateVille(@PathVariable int id, @RequestBody VilleTP6 villeTP6) {
		return villeService.modifierVilleTP6(id, villeTP6);
	}

	@DeleteMapping("/{id}")
	public VilleTP6 deleteVille(@PathVariable int id) {
		return villeRepository.deleteById(id);
	}

}
