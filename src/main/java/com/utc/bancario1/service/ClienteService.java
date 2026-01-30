package com.utc.bancario1.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.utc.bancario1.entity.Cliente;
import com.utc.bancario1.repository.ClienteRepository;
import com.utc.bancario1.repository.CuentaRepository;

@Service
public class ClienteService {
	 private final ClienteRepository cliRepo;
	    private final CuentaRepository cuentaRepo;  

	    public ClienteService(ClienteRepository cliRepo, CuentaRepository cuentaRepo) {
	        this.cliRepo = cliRepo;
	        this.cuentaRepo = cuentaRepo;
	    }

	
	
	 public Cliente guardar(Cliente cliente) {

	        // NUEVO CLIENTE
	        if (cliente.getId() == null) {

	            if (cliRepo.existsByCedula(cliente.getCedula())) {
	                throw new RuntimeException("La cédula ya está registrada");
	            }

	            if (cliRepo.existsByEmail(cliente.getEmail())) {
	                throw new RuntimeException("El email ya está registrado");
	            }

	        }
	        // EDITAR CLIENTE
	        else {

	            if (cliRepo.existsByCedulaAndIdNot(cliente.getCedula(), cliente.getId())) {
	                throw new RuntimeException("La cédula ya está registrada");
	            }

	            if (cliRepo.existsByEmailAndIdNot(cliente.getEmail(), cliente.getId())) {
	                throw new RuntimeException("El email ya está registrado");
	            }
	        }

	        return cliRepo.save(cliente);
	    }
	
	 
	public List<Cliente>listar(){
		return cliRepo.findAll();
	}
	
	 
	public Cliente buscarPorId(Long id_M) {
		return cliRepo.findById(id_M).orElse(null);
	}
	
	 
 
	public void eliminar(Long id) {
	    Cliente cliente = cliRepo.findById(id)
	                             .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

	    if (cuentaRepo.existsByCliente(cliente)) {
	        throw new RuntimeException("El cliente tiene cuentas asociadas. Primero elimine las cuentas.");
	    }

	    cliRepo.deleteById(id);
	}


}
