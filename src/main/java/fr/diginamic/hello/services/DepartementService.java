package fr.diginamic.hello.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.diginamic.hello.dao.DepartementDao;
import fr.diginamic.hello.entites.Departement;
import fr.diginamic.hello.entites.VilleTP6;
import fr.diginamic.hello.repositories.DepartementRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class DepartementService {

	@Autowired
	private DepartementDao departementDao;
	
	@Autowired
	private DepartementRepository depRepo;

	List<Departement> listeDepartements;

	@PostConstruct
	public void initDonnees() {
		this.listeDepartements = extractDepartements();
	}

	public List<Departement> extractDepartements() {
		return departementDao.extractDepartements();
	}

	public Departement extractDeptCodeDep(String codeDep) {
		Departement depParId = listeDepartements.stream().filter(d -> d.getCodeDept() != null && d.getCodeDept().equals(codeDep)).findFirst().orElse(null);
		return depParId;
	}

	public Departement extractDepNom(String nom) {
		Departement depParNom = listeDepartements.stream().filter(d -> d.getNom() != null && d.getNom().equalsIgnoreCase(nom)).findFirst()
				.orElse(null);
		return depParNom;
	}

	
	public void insertDepartement(Departement dept) { //, Set<VilleTP6> listeVilles

		Departement deptExistant = extractDeptCodeDep(dept.getCodeDept());
		if (deptExistant == null) {
//			Departement dep = new Departement();
			depRepo.save(dept);
			listeDepartements.add(dept);
		}
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
	
	public List<VilleTP6> villesPlusPeuplees(long nbrVilles, List<VilleTP6> setVilles) {
		return setVilles.stream().limit(nbrVilles).collect(Collectors.toList());	
	}
	

}
