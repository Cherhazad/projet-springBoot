package fr.diginamic.hello.controleurs;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.hello.dto.VilleTP6Dto;
import fr.diginamic.hello.entites.VilleTP6;
import fr.diginamic.hello.repositories.VilleRepository;
import fr.diginamic.hello.services.VilleMapper;
import fr.diginamic.hello.services.VilleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/ville")
public class VilleControllerRepo {

	@Autowired
	private VilleRepository villeRepository;

	@Autowired
	private VilleService villeService;

	@Operation(summary = "Extraire la liste des villes")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Liste des villes au format JSON", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = VilleTP6Dto.class)) }) })
	@GetMapping
	public ResponseEntity<String> extraireVilles() {
		return ResponseEntity
				.ok(villeRepository.findAll().stream().map(VilleMapper::toDto).collect(Collectors.toList()).toString());
	}

	@GetMapping("/pagination")
	public Page<VilleTP6Dto> extraireVilles(@RequestParam int page, @RequestParam int size) {
		return villeRepository.findAll(PageRequest.of(page, size)).map(VilleMapper::toDto);
	}

	@GetMapping("/parId/{id}")
	public VilleTP6Dto extraireVilleParId(@PathVariable int id) {
		Optional<VilleTP6> ville = villeRepository.findById(id);
		if (ville.isPresent()) {
			return VilleMapper.toDto(ville.get());
		} else {
			throw new EntityNotFoundException("Ville avec l'ID " + id + " non trouvée.");
		}
	}

	@GetMapping("/parNom/{nom}")
	public VilleTP6Dto extractVilleTP6Nom(@PathVariable String nom) {
		VilleTP6 ville = villeRepository.findByNom(nom);
		VilleTP6Dto villeDto = VilleMapper.toDto(ville);
		return villeDto;
	}

	@GetMapping("/commencePar")
	public List<VilleTP6Dto> findVilleStartsWith(@RequestParam String nom) {
		List<VilleTP6> ville = villeRepository.findVilleStartsWith(nom);
		return ville.stream().map(VilleMapper::toDto).collect(Collectors.toList());
	}

	@GetMapping("/populationGreaterThan")
	public List<VilleTP6Dto> findVillePopGreaterThan(@RequestParam int min) {
		List<VilleTP6> ville = villeRepository.findVillePopGreaterThan(min);
		return ville.stream().map(VilleMapper::toDto).collect(Collectors.toList());
	}

	@GetMapping("/populationBetween")
	public List<VilleTP6Dto> findVillePopBetween(@RequestParam int min, @RequestParam int max) {
		List<VilleTP6> ville = villeRepository.findVillePopBetween(min, max);
		return ville.stream().map(VilleMapper::toDto).collect(Collectors.toList());
	}

	@GetMapping("/parDepartementPopGreaterThan")
	public List<VilleTP6Dto> findVilleDeptPopGreater(@RequestParam int min, @RequestParam String depCode) {
		List<VilleTP6> ville = villeRepository.findVillesDeptPopGreater(min, depCode);
		return ville.stream().map(VilleMapper::toDto).collect(Collectors.toList());
	}

	@GetMapping("/parDepartementPopBetween")
	public List<VilleTP6Dto> findVilleDeptPopBetween(@RequestParam int min, @RequestParam int max,
			@RequestParam String depCode) {
		List<VilleTP6> ville = villeRepository.findVilleDeptPopBetween(min, max, depCode);
		return ville.stream().map(VilleMapper::toDto).collect(Collectors.toList());
	}

	@GetMapping("/parDepartementTop")
	public List<VilleTP6Dto> findNVillesParDepartementOrdreDecroissant(@RequestParam String depCode,
			@RequestParam int nbrVilles) {
		Pageable pageable = PageRequest.of(0, nbrVilles);
		List<VilleTP6> ville = villeRepository.findNVillesParDepartementOrdreDecroissant(depCode, pageable);
		return ville.stream().map(VilleMapper::toDto).collect(Collectors.toList());
	}

	@Operation(summary = "Création d'une nouvelle ville")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "La ville a bien été insérée.", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = VilleTP6Dto.class)) }),
			@ApiResponse(responseCode = "400", description = "La ville n'a pas pu être insérée.", content = @Content) })
	@PostMapping
	public ResponseEntity<String> insertVille(@RequestBody VilleTP6 villeTP6) {

		if (villeRepository.findByNom(villeTP6.getNom()) != null) {
			return ResponseEntity.badRequest().body("La ville n'a pas été insérée.");
		}
		villeService.insertVille(villeTP6);
		return ResponseEntity.ok(villeService.extractVilleTP6s().toString());

	}

	@Operation(summary = "Modification d'une ville")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "La ville a bien été modifiée.", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = VilleTP6Dto.class)) }),
			@ApiResponse(responseCode = "400", description = "La ville n'a pas pu être modifiée.", content = @Content) })
	@PutMapping("/{id}")
	public ResponseEntity<String> updateVille(@PathVariable int id, @RequestBody VilleTP6 villeTP6) {
		if (villeRepository.findById(villeTP6.getId()) != null) {
			return ResponseEntity.badRequest().body("La ville n'a pas été modifiée.");
		}
		List<VilleTP6> ville = villeService.modifierVilleTP6(id, villeTP6);
		ville.stream().map(VilleMapper::toDto).collect(Collectors.toList());
		return ResponseEntity.ok(villeService.extractVilleTP6s().toString());
	}
	
	@Operation(summary = "Suppression d'une ville")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "La ville a bien été supprimée.", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = VilleTP6Dto.class)) }),
			@ApiResponse(responseCode = "400", description = "La ville n'a pas pu être supprimée.", content = @Content) })
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteVille(@PathVariable int id, VilleTP6 villeTP6) {
		if (villeRepository.findById(villeTP6.getId()) != null) {
			return ResponseEntity.badRequest().body("La ville n'a pas été supprimée car l'id est inexistant.");
		}
		villeRepository.deleteById(id);
		return ResponseEntity.ok(villeService.extractVilleTP6s().toString());
	}

}
