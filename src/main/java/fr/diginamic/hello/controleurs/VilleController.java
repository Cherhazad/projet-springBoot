package fr.diginamic.hello.controleurs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.hello.entites.Ville;

@RestController
@RequestMapping("/villes")
public class VilleController {

	
	@GetMapping("/france")
	public List<Ville> listerVilles(){
		return List.of(new Ville("Montpellier", 36000), new Ville("Paris", 85000));
	}

}
