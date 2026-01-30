package com.utc.bancario1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utc.bancario1.entity.Cliente;
import com.utc.bancario1.entity.Cuenta;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
	   boolean existsByNumeroCuenta(String numeroCuenta);

	    boolean existsByNumeroCuentaAndIdNot(String numeroCuenta, Long id);
	    boolean existsByCliente(Cliente cliente);
	    

}
