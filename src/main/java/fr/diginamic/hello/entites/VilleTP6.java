package fr.diginamic.hello.entites;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "VILLE")
public class VilleTP6 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// @Min(value = 1) on ne peut pas faire de contrôle sur l'id en auto incrément car géré par mySql
	private int id;
	
	@NotNull
	private String nom;
	
	private int nbHabitants;
	
	@ManyToOne
	@JoinColumn(name = "ID_DEPARTEMENT")
	private Departement departements;

	/**
	 * Constructeur
	 * 
	 */
	public VilleTP6() {
		super();
	}

	/**
	 * Constructeur
	 * 
	 * @param nom
	 * @param nbHabitants
	 */

	public VilleTP6(int id, String nom, int nbHabitants) {
		super();
		this.id = id;
		this.nom = nom;
		this.nbHabitants = nbHabitants;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
		return false;
		VilleTP6 other = (VilleTP6) obj;
		return nbHabitants == other.nbHabitants && Objects.equals(nom, other.nom);
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
	 * Getter pour id
	 * 
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter pour id
	 * 
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "VilleTP6 [id=" + id + ", nom=" + nom + ", nbHabitants=" + nbHabitants + "]";
	}
	
	

}
