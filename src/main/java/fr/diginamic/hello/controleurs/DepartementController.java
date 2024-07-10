package fr.diginamic.hello.controleurs;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import fr.diginamic.hello.dto.DepartementDto;
import fr.diginamic.hello.entites.Departement;
import fr.diginamic.hello.repositories.DepartementRepository;
import fr.diginamic.hello.services.DepartementService;
import fr.diginamic.hello.services.VilleMapper;

@RestController
@RequestMapping("/departement")
public class DepartementController {

	@Autowired
	private DepartementService depService;

	@Autowired
	private DepartementRepository depRepository;

	@Autowired
	private ObjectMapper objectMapper;

	@GetMapping
	public ResponseEntity<String> extraireDepartement() {
		return ResponseEntity.ok(
				depRepository.findAll().stream().map(VilleMapper::depToDto).collect(Collectors.toList()).toString());
	}

	@GetMapping("/parCodeDep/{codeDep}")
	public ResponseEntity<String> extraireDepParId(@PathVariable String codeDep) throws JsonProcessingException {
		Departement dep = depRepository.findByCodeDep(codeDep);
		if (dep != null) {
			DepartementDto depDto = VilleMapper.depToDto(dep);
			ObjectWriter writer = objectMapper.writerWithDefaultPrettyPrinter();
			String json = writer.writeValueAsString(depDto);
			return ResponseEntity.ok(json);
		} else {
			return ResponseEntity.badRequest().body("Département avec le code " + codeDep + " non trouvé.");
		}
	}

	@GetMapping("/parNom/{nom}")
	public ResponseEntity<String> extractDepNom(@PathVariable String nom) throws JsonProcessingException {
		Departement dep = depService.extractDepNom(nom);
		if (dep != null) {
			DepartementDto depDto = VilleMapper.depToDto(dep);
			ObjectWriter writer = objectMapper.writerWithDefaultPrettyPrinter();
			String json = writer.writeValueAsString(depDto);
			return ResponseEntity.ok(json);
		} else {
			return ResponseEntity.badRequest().body("Département avec le nom " + nom + " non trouvé.");
		}
	}

//	@PostMapping
//	public List<Departement> insertDepartement(@RequestBody Departement dept) {
//		return depService.insertDepartement(dept);
//	}

	@PutMapping("/{codeDep}")
	public ResponseEntity<String> updateVille(@PathVariable String codeDep, @RequestBody Departement dept)
			throws JsonProcessingException {

		Departement dep = depRepository.findByCodeDep(codeDep);
		if (dep != null) {
			Departement depModifie = depService.modifierDepartement(codeDep, dept);
			DepartementDto depDto = VilleMapper.depToDto(depModifie);
			ObjectWriter writer = objectMapper.writerWithDefaultPrettyPrinter();
			String json = writer.writeValueAsString(depDto);
			return ResponseEntity.ok(json);
		} else {
			return ResponseEntity.badRequest().body("Département avec le codeDep " + codeDep + " non trouvé.");
		}
	}

	@DeleteMapping("/{codeDep}")
	public ResponseEntity<String> deleteDepartement(@PathVariable String codeDep) throws JsonProcessingException {
		Departement dep = depRepository.findByCodeDep(codeDep);
		if (dep != null) {
			DepartementDto depDto = VilleMapper.depToDto(dep);
			depRepository.deleteByCodeDep(codeDep);
			ObjectWriter writer = objectMapper.writerWithDefaultPrettyPrinter();
			String json = writer.writeValueAsString(depDto);
			return ResponseEntity.ok(json);
		}
		return ResponseEntity.badRequest().body("Le département n'a pas été supprimé car le code est inexistant.");
	}

}
