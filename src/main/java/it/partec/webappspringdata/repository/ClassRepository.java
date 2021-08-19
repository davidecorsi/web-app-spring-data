package it.partec.webappspringdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.partec.webappspringdata.model.Class;

public interface ClassRepository extends JpaRepository<Class, Long>{
	
	public Class findByName(String name);
}
