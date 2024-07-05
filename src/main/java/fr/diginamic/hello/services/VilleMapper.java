package fr.diginamic.hello.services;

import org.springframework.stereotype.Component;

import fr.diginamic.hello.DTO.VilleTP6Dto;
import fr.diginamic.hello.entites.VilleTP6;

/**
 * Classe qui convertit une entité JPA de type VilleTP6 en VilleDto afin de
 * n'afficher dans le Json que les éléments ouhaités et éviter les boucles
 * infinies lors des associations de départements à des villes.
 * 
 */
@Component
public class VilleMapper {

	public static VilleTP6Dto toDto(VilleTP6 villeTP6) {

		VilleTP6Dto villeDto = new VilleTP6Dto();
		if (villeTP6.getDepartement() == null) {
			villeDto.setCodeDep(null);
		} else {			
			villeDto.setCodeDep(villeTP6.getDepartement().getCodeDept());
		}
		villeDto.setNom(villeTP6.getNom());
		villeDto.setNbHabitants(villeTP6.getNbHabitants());

		return villeDto;

	}
}
