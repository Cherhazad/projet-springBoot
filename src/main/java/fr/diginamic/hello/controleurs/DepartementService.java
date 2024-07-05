package fr.diginamic.hello.controleurs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.diginamic.hello.DAO.DepartementDao;
import fr.diginamic.hello.entites.Departement;
import jakarta.annotation.PostConstruct;

@Service
public class DepartementService {

	@Autowired
	private DepartementDao departementDao;

	List<Departement> listeDepartements;

	@PostConstruct
	public void initDonnees() {
		this.listeDepartements = extractDepartements();
	}

	public List<Departement> extractDepartements() {
		return departementDao.extractDepartements();
	}

	public Departement extractDeptId(String codeDep) {

		Departement depParId = listeDepartements.stream().filter(d -> d.getCodeDept() == codeDep).findFirst().orElse(null);
		return depParId;
	}

	public Departement extractDepNom(String nom) {
		Departement depParNom = listeDepartements.stream().filter(d -> d.getNom().equalsIgnoreCase(nom)).findFirst()
				.orElse(null);
		return depParNom;
	}

	public List<Departement> insertDepertement(Departement dept) {

		Departement deptExistant = listeDepartements.stream().filter(d -> d.getNom().equals(dept.getNom())).findFirst()
				.orElse(null);
		if (deptExistant == null) {
			listeDepartements.add(dept);
			departementDao.insert(dept);
			System.out.println(listeDepartements);
		}
		return listeDepartements;
	}
	
	public List<Departement> modifierDepartement(String codeDep, Departement deptModifie) {
		Departement deptAModifier = listeDepartements.stream().filter(d -> d.getCodeDept() == codeDep).findFirst().orElse(null);
		if (deptAModifier != null) {
			System.out.println("Département trouvé : " + deptAModifier);
			deptAModifier.setNom(codeDep);
			departementDao.update(deptModifie);
			System.out.println("Département modifié : " + deptModifie);
			System.out.println(listeDepartements);
		} else {
            System.out.println("DepartementService : Département avec codeDep " + codeDep + " non trouvée.");
        }
		return listeDepartements;
	}
	
	public List<Departement> supprimerDepartement(String codeDep) {
		Departement deptParId = listeDepartements.stream().filter(d -> d.getCodeDept() == codeDep).findFirst()
				.orElse(null);
		if (deptParId != null) {
			listeDepartements.remove(deptParId);
			departementDao.delete(deptParId);
			System.out.println(listeDepartements);
		}
		return listeDepartements;
	}
	

}
