package it.partec.webappspringdata.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.partec.webappspringdata.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
	
	public Student findByNameAndSurname(String name, String surname);
	
	/*
	 * @Query("SELECT s FROM Student s JOIN FETCH s.classe") public List<Student>
	 * findAll();
	 * 
	 * @Query("SELECT s FROM Student s JOIN FETCH s.classe c WHERE s.id = ?1")
	 * public Student getById(Long id);
	 */
}
