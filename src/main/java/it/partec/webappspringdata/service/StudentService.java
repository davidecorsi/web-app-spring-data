package it.partec.webappspringdata.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.partec.webappspringdata.model.Class;
import dto.StudentDto;
import it.partec.webappspringdata.exception.StudentException;
import it.partec.webappspringdata.model.Student;
import it.partec.webappspringdata.repository.ClassRepository;
import it.partec.webappspringdata.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private ClassRepository classRepository;

	@Transactional(readOnly = true)
	public List<StudentDto> getListStudent() throws Exception {
		List<StudentDto> studentDtoList = new ArrayList<>();
		try {
			List<Student> studentList = studentRepository.findAll();
			for(Student s: studentList) {
				StudentDto studentDto = new StudentDto();
				studentDto.setId(s.getId());
				studentDto.setName(s.getName());
				studentDto.setSurname(s.getSurname());
				studentDto.setAge(s.getAge());
				studentDto.setClassName(s.getClasse().getName());
				studentDtoList.add(studentDto);
			}
		} catch(Exception e) {
			e.printStackTrace();
			throw new StudentException(e);
		}
		return studentDtoList;
	}
	
	@Transactional(readOnly = true)
	public StudentDto getStudent(long id) throws Exception {
		StudentDto studentDto = new StudentDto();
		try {
			Student student = studentRepository.getById(id);
			studentDto.setId(student.getId());
			studentDto.setName(student.getName());
			studentDto.setSurname(student.getSurname());
			studentDto.setAge(student.getAge());
			studentDto.setClassName(student.getClasse().getName());
		} catch(Exception e) {
			e.printStackTrace();
			throw new StudentException(e);
		}
		return studentDto;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void addStudent(StudentDto studentDto) throws Exception {
		try {
			Class classe = classRepository.findByName(studentDto.getClassName());
			if(classe == null) {
				classe = new Class();
				classe.setName(studentDto.getClassName());
				classRepository.save(classe);
			}
			Student student = new Student();
			student.setId(studentDto.getId());
			student.setName(studentDto.getName());
			student.setSurname(studentDto.getSurname());
			student.setAge(studentDto.getAge());
			student.setClasse(classe);
			studentRepository.save(student);
		} catch(Exception e) {
			e.printStackTrace();
			throw new StudentException(e);
		}
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void deleteStudent(long id) throws Exception{
		try {
			studentRepository.deleteById(id);
		} catch(Exception e) {
			e.printStackTrace();
			throw new StudentException(e);
		}
	}
}
