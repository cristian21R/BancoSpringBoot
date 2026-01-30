package com.utc.bancario1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utc.bancario1.entity.Cliente;

@Repository
public interface ClienteRepository  extends JpaRepository<Cliente,Long>{
	
	 boolean existsByCedula(String cedula);

	    boolean existsByEmail(String email);
 
	    boolean existsByCedulaAndIdNot(String cedula, Long id);

	    boolean existsByEmailAndIdNot(String email, Long id);

	    
}
