package it.partec.webappspringdata.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import it.partec.webappspringdata.dto.StudentDto;
import it.partec.webappspringdata.model.Student;

@Repository
public class StudentCustomRepository {
	
	@Autowired
    private EntityManager em;
	
	public List<Student> searchStudent(StudentDto studentDto) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Student> crtQ = cb.createQuery(Student.class);
        Root<Student> root = crtQ.from(Student.class);
        root.fetch("classe");
        crtQ.select(root);
        List<Predicate> predicates = new ArrayList<>();
        if(studentDto.getName() != null && !studentDto.getName().equals("")) {
            predicates.add(cb.like(root.get("name"), "%" + studentDto.getName() + "%"));
        }
        if(studentDto.getSurname() != null && !studentDto.getSurname().equals("")) {
            predicates.add(cb.like(root.get("surname"), "%" + studentDto.getSurname() + "%"));
        }
        if(studentDto.getAge() != null) {
        	predicates.add(cb.equal(root.get("age"), studentDto.getAge()));
        }
        if(studentDto.getClassName() != null && !studentDto.equals("")) {
        	predicates.add(cb.equal(root.get("classe").get("name"), studentDto.getClassName()));
        }
        crtQ.where(predicates.toArray(new Predicate[0]));
        return em.createQuery(crtQ).getResultList();
	}
}
