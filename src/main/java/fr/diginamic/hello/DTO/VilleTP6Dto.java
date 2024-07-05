package fr.diginamic.hello.DTO;

public class VilleTP6Dto {

	private String nom;
	private int nbHabitants;
	private String codeDep;

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

	@Override
	public String toString() {
		return "VilleTP6Dto [nom=" + nom + ", nbHabitants=" + nbHabitants + ", codeDep=" + codeDep + "]";
	}

}
