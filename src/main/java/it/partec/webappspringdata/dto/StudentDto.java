package it.partec.webappspringdata.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;

public class StudentDto implements Serializable {
	
	private Long id;
	
	@Length(min = 3, max = 20, message = "il campo name deve essere compreso tra 3 e 20")
	private String name;
	
	@Length(min = 3, max = 20, message = "il campo surname deve essere compreso tra 3 e 20")
	private String surname;
	
	@Positive(message = "il campo age deve essere un numero positivo")
	private Long age;
	
	@NotBlank(message = "il campo className non deve essere vuoto")
	private String className;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public Long getAge() {
		return age;
	}
	public void setAge(Long age) {
		this.age = age;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
}
