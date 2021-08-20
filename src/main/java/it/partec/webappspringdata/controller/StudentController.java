package it.partec.webappspringdata.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.partec.webappspringdata.dto.StudentDto;
import it.partec.webappspringdata.service.StudentService;


@RestController
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@GetMapping
	public ResponseEntity<List<StudentDto>> getListStudent() {
		List<StudentDto> studentList = null;
		try {
			studentList = studentService.getListStudent();
		} catch(Exception e) {
			return new ResponseEntity<List<StudentDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<StudentDto>>(studentList, HttpStatus.OK);
	}
	
	/*
	 * @GetMapping public ResponseEntity<List<StudentDto>> getSearchStudent(
	 * 
	 * @RequestParam(name = "name", required = false) String name,
	 * 
	 * @RequestParam(name = "surname", required = false) String surname,
	 * 
	 * @RequestParam(name = "age", required = false) Long age,
	 * 
	 * @RequestParam(name = "className", required = false) String className) {
	 * StudentDto studentDto = new StudentDto(); studentDto.setName(name);
	 * studentDto.setSurname(surname); studentDto.setAge(age);
	 * studentDto.setClassName(className); List<StudentDto> studentList = null; try
	 * { studentList = studentService.searchStudent(studentDto); } catch(Exception
	 * e) { return new
	 * ResponseEntity<List<StudentDto>>(HttpStatus.INTERNAL_SERVER_ERROR); } return
	 * new ResponseEntity<List<StudentDto>>(studentList, HttpStatus.OK); }
	 */
	
	@GetMapping("/{id}")
	public ResponseEntity<StudentDto> getStudent(@PathVariable("id") long id) {
		StudentDto studentDto = null;
		try {
			studentDto = studentService.getStudent(id);
		} catch(Exception e) {
			return new ResponseEntity<StudentDto>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(studentDto != null) {
			return new ResponseEntity<StudentDto>(studentDto, HttpStatus.OK);
		}
		return new ResponseEntity<StudentDto>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping
	public ResponseEntity<Object> addStudent(@RequestBody StudentDto studentDto) {
		try {
			studentService.addStudent(studentDto);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteStudent(@PathVariable("id") long id) {
		try {
			studentService.deleteStudent(id);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<Object> updateStudent(@RequestBody StudentDto studentDto) {
		try {
			studentService.updateStudent(studentDto);
		} catch(EntityNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
