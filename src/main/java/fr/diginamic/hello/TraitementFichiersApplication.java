package fr.diginamic.hello;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.diginamic.hello.utils.VilleParseur;

@SpringBootApplication
public class TraitementFichiersApplication implements CommandLineRunner {
	
	@Autowired
	private VilleParseur villeParseur; 

	@Value("${initialisation.base}")
	private boolean initialisationBase;

	public static void main(String[] args) {

		SpringApplication application = new SpringApplication(TraitementFichiersApplication.class);
		application.setWebApplicationType(WebApplicationType.NONE);
		application.run(args);

	}

	@Override
	public void run(String... args) throws Exception {

		if (!initialisationBase) {
			System.out.println("La base est déjà initialisée");
			return;
		}

		System.out.println("Je suis déclenché !");

		Path path = Paths.get("src/main/resources/recensement.csv");
		villeParseur.readCsvAndInsertInDatabase(path);

	}
	
}
