package it.partec.webappspringdata.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.partec.webappspringdata.dto.StudentDto;
import it.partec.webappspringdata.service.impl.StudentServiceImpl;


@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentServiceImpl studentService;

		@GetMapping
		public ResponseEntity<List<StudentDto>> getListStudent() throws Exception {
			List<StudentDto> studentList = null;
			studentList = studentService.getListStudent();
			return new ResponseEntity<List<StudentDto>>(studentList, HttpStatus.OK);
		}


//	@GetMapping public ResponseEntity<List<StudentDto>> getSearchStudent(
//			@RequestParam(name = "name", required = false) String name,
//			@RequestParam(name = "surname", required = false) String surname,
//			@RequestParam(name = "age", required = false) Long age,
//			@RequestParam(name = "className", required = false) String className) throws Exception {
//		StudentDto studentDto = new StudentDto(); studentDto.setName(name);
//		studentDto.setSurname(surname); studentDto.setAge(age);
//		studentDto.setClassName(className); 
//		List<StudentDto> studentList = null; 
//		studentList = studentService.searchStudent(studentDto);
//		return new ResponseEntity<List<StudentDto>>(studentList, HttpStatus.OK); 
//	}


	@GetMapping("/{id}")
	public ResponseEntity<StudentDto> getStudent(@PathVariable("id") long id) throws Exception {
		StudentDto studentDto = null;
		studentDto = studentService.getStudent(id);
		return new ResponseEntity<StudentDto>(studentDto, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Object> addStudent(@RequestBody StudentDto studentDto) throws Exception {
		studentService.addStudent(studentDto);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteStudent(@PathVariable("id") long id) throws Exception {
		studentService.deleteStudent(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}