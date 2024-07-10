package fr.diginamic.hello.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class DepartementDto {

	@NotNull
	@Size(min = 2, max = 255)
	private String nomDepartement;
	
	@Min(value = 1)
	private int nbHabitants;
	
	@NotNull
	@Size(min = 2, max = 3)
	private String codeDep;

	/**
	 * Constructeur
	 * 
	 */
	public DepartementDto() {
		super();
	}

	/** Getter pour nomDepartement
	 * @return the nomDepartement
	 */
	public String getNomDepartement() {
		return nomDepartement;
	}

	/** Setter pour nomDepartement
	 * @param nomDepartement the nomDepartement to set
	 */
	public void setNomDepartement(String nomDepartement) {
		this.nomDepartement = nomDepartement;
	}

	/** Getter pour nbHabitants
	 * @return the nbHabitants
	 */
	public int getNbHabitants() {
		return nbHabitants;
	}

	/** Setter pour nbHabitants
	 * @param nbHabitants the nbHabitants to set
	 */
	public void setNbHabitants(int nbHabitants) {
		this.nbHabitants = nbHabitants;
	}

	/** Getter pour codeDep
	 * @return the codeDep
	 */
	public String getCodeDep() {
		return codeDep;
	}

	/** Setter pour codeDep
	 * @param codeDep the codeDep to set
	 */
	public void setCodeDep(String codeDep) {
		this.codeDep = codeDep;
	}

	@Override
	public String toString() {
		return "DepartementDto [nomDepartement=" + nomDepartement + ", nbHabitants=" + nbHabitants + ", codeDep="
				+ codeDep + "]";
	}
	

	
}
