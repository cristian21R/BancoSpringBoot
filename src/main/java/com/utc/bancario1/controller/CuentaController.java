package com.utc.bancario1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.utc.bancario1.entity.Cliente;
import com.utc.bancario1.entity.Cuenta;
import com.utc.bancario1.service.ClienteService;
import com.utc.bancario1.service.CuentaService;


@Controller
@RequestMapping("/cuentas")
public class CuentaController {

    private final CuentaService cuentaService;
    private final ClienteService clienteService;

    public CuentaController(CuentaService cuentaService, ClienteService clienteService) {
        this.cuentaService = cuentaService;
        this.clienteService = clienteService;
    }
    
    @GetMapping
    public String listarTodasCuentas(Model model) {
        model.addAttribute("listadoCuentas", cuentaService.listar());
        return "cuentas"; // Asegúrate de que exista src/main/resources/templates/cuentas.html
    }

 
    @GetMapping("/editar/{id}")
    public String editarCuenta(@PathVariable Long id, Model model) {
        Cuenta cuenta = cuentaService.buscarPorId(id);
        if (cuenta == null) {
            return "redirect:/cuentas"; //  
        }
        model.addAttribute("cuentaEdit", cuenta);
        model.addAttribute("cliente", cuenta.getCliente());
        return "editarCuenta"; 
    }
    
  
 
    
    @PostMapping("/actualizar/{id}")
    public String actualizarCuenta(@PathVariable Long id,
                                   @ModelAttribute Cuenta cuentaEdit,
                                   RedirectAttributes redirectAttrs) {
        try {
            cuentaService.actualizar(id, cuentaEdit);
            redirectAttrs.addFlashAttribute("mensaje", "Cuenta actualizada correctamente");
            redirectAttrs.addFlashAttribute("tipo", "success");
        } catch (RuntimeException e) {
            redirectAttrs.addFlashAttribute("mensaje", e.getMessage());
            redirectAttrs.addFlashAttribute("tipo", "error");
            return "redirect:/cuentas";
        }

        return "redirect:/cuentas";
    }

  
    @GetMapping("/nuevo/{clienteId}")
    public String nuevaCuenta(@PathVariable Long clienteId, Model model) {
        Cliente cliente = clienteService.buscarPorId(clienteId);
        if (cliente == null) {
            model.addAttribute("mensaje", "Cliente no encontrado");
            return "redirect:/clientes";
        }

        Cuenta cuenta = new Cuenta();
        model.addAttribute("cliente", cliente);
        model.addAttribute("nuevaCuenta", cuenta);
        return "nuevaCuenta";  
    }

     
    @PostMapping("/guardar/{clienteId}")
    public String guardarCuenta(@PathVariable Long clienteId,
                                @ModelAttribute Cuenta cuenta,
                                RedirectAttributes redirectAttrs) {

        Cliente cliente = clienteService.buscarPorId(clienteId);
        if (cliente == null) {
            redirectAttrs.addFlashAttribute("mensaje", "Cliente no encontrado");
            redirectAttrs.addFlashAttribute("tipo", "error");
            return "redirect:/clientes";
        }

         cuenta.setCliente(cliente);

         try {
             cuentaService.guardar(cuenta);
             redirectAttrs.addFlashAttribute("mensaje", "Cuenta guardada correctamente");
             redirectAttrs.addFlashAttribute("tipo", "success");
         } catch (RuntimeException e) {
             redirectAttrs.addFlashAttribute("mensaje", e.getMessage());
             redirectAttrs.addFlashAttribute("tipo", "error");
             return "redirect:/cuentas";
         }

        return "redirect:/cuentas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCuenta(@PathVariable Long id, RedirectAttributes redirectAttrs) {
        Cuenta cuenta = cuentaService.buscarPorId(id);
        if (cuenta == null) {
            redirectAttrs.addFlashAttribute("mensaje", "Cuenta no encontrada");
            redirectAttrs.addFlashAttribute("tipo", "error");
            return "redirect:/clientes";
        }

        Long clienteId = cuenta.getCliente().getId();
        cuentaService.eliminar(id);

        redirectAttrs.addFlashAttribute("mensaje", "Cuenta eliminada correctamente");
        redirectAttrs.addFlashAttribute("tipo", "success");
        return "redirect:/cuentas";
    }

}
