package fr.diginamic.hello.entites;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Departement {
	
	@Id
	private String codeDept;
	private String nom;
	
	@OneToMany(mappedBy = "departement")
	private Set<VilleTP6> villes = new HashSet<>();
	
	
	/** Constructeur
	 * 
	 */
	public Departement() {
		super();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Departement other = (Departement) obj;
		return Objects.equals(codeDept, other.codeDept);
	}
	
	/** Getter pour codeDept
	 * @return the codeDept
	 */
	public String getCodeDept() {
		return codeDept;
	}


	/** Setter pour codeDept
	 * @param codeDept the codeDept to set
	 */
	public void setCodeDept(String codeDept) {
		this.codeDept = codeDept;
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


	/** Getter pour villes
	 * @return the villes
	 */
	public Set<VilleTP6> getVilles() {
		return villes;
	}



	/** Setter pour villes
	 * @param villes the villes to set
	 */
	public void setVilles(Set<VilleTP6> villes) {
		this.villes = villes;
	}


	@Override
	public String toString() {
		return "Departement [codeDept=" + codeDept + ", nom=" + nom + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(codeDept);
	}

	
}
