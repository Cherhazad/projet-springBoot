package fr.diginamic.hello.entites;

public class Ville {
	
	private String nom;
	private int nbHabitants;
	

	/** Constructeur
	 * @param nom
	 * @param nbHabitants
	 */
	
	
	public Ville(String nom, int nbHabitants) {
		super();
		this.nom = nom;
		this.nbHabitants = nbHabitants;
	}
	
	/** Getter pour nom
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}
	/** Setter pour nom
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
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
	
	
	
}
