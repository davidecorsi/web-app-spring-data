package it.partec.webappspringdata.repository;

import javax.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import it.partec.webappspringdata.model.Student;

@DataJpaTest
public class StudentRepositoryTests {

	@Autowired
	private StudentRepository studentRepository;
	
	@Test
	void getByIdTest() {
		Student student = studentRepository.getById(1L);
		assertAll(() -> student.getName());
	}
	
	@Test
	void getByIdNullTest() {
		Student student = studentRepository.getById(6L);
		assertThrows(EntityNotFoundException.class, () -> student.getName());
	}
}
