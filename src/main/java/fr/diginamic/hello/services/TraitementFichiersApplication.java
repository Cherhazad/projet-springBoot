package fr.diginamic.hello.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.diginamic.hello.controleurs.DepartementController;
import fr.diginamic.hello.dao.DepartementDao;
import fr.diginamic.hello.dao.VilleDao;
import fr.diginamic.hello.entites.Departement;
import fr.diginamic.hello.entites.VilleTP6;

@SpringBootApplication
public class TraitementFichiersApplication implements CommandLineRunner {

	@Autowired
	private DepartementController depCtroller;

	@Autowired
	private DepartementDao depDao;

	@Autowired
	private VilleDao villeDao;
	
	@Autowired
	private VilleService villeService;

	public static void main(String[] args) {

		SpringApplication application = new SpringApplication(TraitementFichiersApplication.class);
		application.setWebApplicationType(WebApplicationType.NONE);
		application.run(args);

	}

	@Override
	public void run(String... args) throws Exception {

		Set<VilleTP6> setVilles = new HashSet<>();
		Set<Departement> setDepartements = new HashSet<>();
		System.out.println("Je suis déclenché !");

		Path path = Paths.get("src/main/resources/recensement.csv");

		List<String> lignes;
		try {
			lignes = Files.readAllLines(path);
			lignes.remove(0);

			for (String ligne : lignes) {

				String[] elementsVille = ligne.split(";");
				VilleTP6 v = new VilleTP6();
				Departement d = new Departement();

				String codeDepartement = elementsVille[2];
				String nomVille = elementsVille[6];
				int populationTotale = Integer.parseInt(elementsVille[9].replace(" ", "").trim());

				d.setCodeDept(codeDepartement);
				v.setDepartement(d);
				v.setNom(nomVille);
				v.setNbHabitants(populationTotale);

				setDepartements.add(d);
				setVilles.add(v);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (Departement departement : setDepartements) {
			depDao.insert(departement);
		}

		List<VilleTP6> sortedVilles = new ArrayList<>(setVilles);
		Collections.sort(sortedVilles, Comparator.reverseOrder());

		List<VilleTP6> villeLimit = depCtroller.villesPlusPeuplees(1000, sortedVilles);

		villeService.insertToutesVilles(villeLimit);

	}
}
