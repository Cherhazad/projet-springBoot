package fr.diginamic.hello.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.diginamic.hello.entites.Departement;
import fr.diginamic.hello.entites.VilleTP6;

@Repository
public interface VilleRepository extends CrudRepository<VilleTP6, Integer>{

	List<VilleTP6> findAll();
	
	VilleTP6 findById(int id);
	
	VilleTP6 findByNom(String nom);
	
	@Override
	VilleTP6 save(VilleTP6 villeTP6);
	
	VilleTP6 deleteById(int id);
	
	@Query("SELECT v FROM VilleTP6 v WHERE v.nom LIKE :nom%")
    List<VilleTP6> findVilleStartsWith(@Param("nom") String nom);
	
	@Query("SELECT v FROM VilleTP6 v WHERE v.nbHabitants > :min")
    List<VilleTP6> findVillePopGreaterThan(@Param("min") int min);
	
	@Query("SELECT v FROM VilleTP6 v WHERE v.nbHabitants BETWEEN :min AND :max")
    List<VilleTP6> findVillePopBetween(@Param("min") int min, @Param("max") int max);
	
	@Query("SELECT v FROM VilleTP6 v JOIN FETCH v.departement d WHERE v.nbHabitants > :min AND d = :dep")
    List<VilleTP6> findVilleDeptPopGreater(@Param("min") int min, @Param("dep") Departement dep);
	
	@Query("SELECT v FROM VilleTP6 v JOIN FETCH v.departement d WHERE v.nbHabitants BETWEEN :min AND :max AND d = :dep")
    List<VilleTP6> findVilleDeptPopBetween(@Param("min") int min, @Param("max") int max,  @Param("dep") Departement dep);
	
	List<VilleTP6> findTop10ByDepartementOrderByNbHabitantsDesc(Departement dep);
	
	
	

	
	
	
	
	
	
	
	
	
}
