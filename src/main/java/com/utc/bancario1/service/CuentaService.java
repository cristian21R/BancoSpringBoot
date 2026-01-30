package com.utc.bancario1.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.utc.bancario1.entity.Cuenta;
import com.utc.bancario1.repository.CuentaRepository;

@Service
public class CuentaService {

    private final CuentaRepository cuentaRepo;

    public CuentaService(CuentaRepository cuentaRepo) {
        this.cuentaRepo = cuentaRepo;
    }

   
   
    
    public Cuenta guardar(Cuenta cuenta) {
        // Validación de número de cuenta duplicado
        if (cuentaRepo.existsByNumeroCuenta(cuenta.getNumeroCuenta())) {
            throw new RuntimeException(
                "Ya existe una cuenta con registra con ese número " 
            );
        }
        return cuentaRepo.save(cuenta);
    }
    
    
    public Cuenta actualizar(Long id, Cuenta cuentaEdit) {
        Cuenta cuenta = cuentaRepo.findById(id)
                                  .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

        // Validación de número de cuenta duplicado, ignorando la cuenta actual
        if (cuentaRepo.existsByNumeroCuentaAndIdNot(cuentaEdit.getNumeroCuenta(), id)) {
            throw new RuntimeException("Ya existe otra cuenta con ese número");
        }

        // Actualizar los campos
        cuenta.setNumeroCuenta(cuentaEdit.getNumeroCuenta());
        cuenta.setSaldo(cuentaEdit.getSaldo());

        return cuentaRepo.save(cuenta);
    }

    // Listar todas las cuentas
    public List<Cuenta> listar() {
        return cuentaRepo.findAll();
    }

     
    // Buscar cuenta por ID
    public Cuenta buscarPorId(Long id) {
        return cuentaRepo.findById(id).orElse(null);
    }

    // Eliminar cuenta
    public void eliminar(Long id) {
        cuentaRepo.deleteById(id);
    }
}
