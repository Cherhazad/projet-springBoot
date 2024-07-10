package fr.diginamic.hello.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class VilleTP6Dto {

	@NotNull
	@Size(min = 2, max = 255)
	private String nom;

	@Min(value = 1)
	private int nbHabitants;

	@NotNull
	@Size(min = 2, max = 3)
	private String codeDep;

	@NotNull
	@Size(min = 2)
	private String nomDepartement;

	/**
	 * Constructeur
	 * 
	 */
	public VilleTP6Dto() {
		super();
	}

	/**
	 * Getter pour nom
	 * 
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Setter pour nom
	 * 
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Getter pour nbHabitants
	 * 
	 * @return the nbHabitants
	 */
	public int getNbHabitants() {
		return nbHabitants;
	}

	/**
	 * Setter pour nbHabitants
	 * 
	 * @param nbHabitants the nbHabitants to set
	 */
	public void setNbHabitants(int nbHabitants) {
		this.nbHabitants = nbHabitants;
	}

	/**
	 * Getter pour codeDep
	 * 
	 * @return the codeDep
	 */
	public String getCodeDep() {
		return codeDep;
	}

	/**
	 * Setter pour codeDep
	 * 
	 * @param codeDep the codeDep to set
	 */
	public void setCodeDep(String codeDep) {
		this.codeDep = codeDep;
	}

	/**
	 * Getter pour nomDepartement
	 * 
	 * @return the nomDepartement
	 */
	public String getNomDepartement() {
		return nomDepartement;
	}

	/**
	 * Setter pour nomDepartement
	 * 
	 * @param nomDepartement the nomDepartement to set
	 */
	public void setNomDepartement(String nomDepartement) {
		this.nomDepartement = nomDepartement;
	}

	@Override
	public String toString() {
		return "VilleTP6Dto [nom=" + nom + ", nbHabitants=" + nbHabitants + ", codeDep=" + codeDep + ", nomDepartement="
				+ nomDepartement + "]";
	}

}
