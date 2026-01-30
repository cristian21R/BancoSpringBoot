package com.utc.bancario1.controller;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.utc.bancario1.entity.Cliente;
import com.utc.bancario1.entity.Cuenta;
import com.utc.bancario1.service.ClienteService;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    // Constructor
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }
 
    @GetMapping
    public String listarClientes(Model model) {
        model.addAttribute("listadoClientes", clienteService.listar());
        model.addAttribute("clienteNuevo", new Cliente());
        return "clientes"; 
    }

   
    @GetMapping("/nuevoCliente")
    public String nuevoCliente(Model model) {
        model.addAttribute("clienteNuevo", new Cliente());
        return "nuevoCliente";  
    }

   
    @PostMapping("/guardar")
    public String guardarCliente(
            @ModelAttribute Cliente cliente,
            RedirectAttributes redirectAttrs) {

        try {
            clienteService.guardar(cliente);
            redirectAttrs.addFlashAttribute("mensaje", "Cliente guardado correctamente");
            redirectAttrs.addFlashAttribute("tipo", "success");

        } catch (RuntimeException e) {
            redirectAttrs.addFlashAttribute("mensaje", e.getMessage());
            redirectAttrs.addFlashAttribute("tipo", "error");
        }

        return "redirect:/clientes";
    }

  
     
    @GetMapping("/editar/{id}")
    public String editarCliente(@PathVariable Long id, Model model) {
        Cliente cliente = clienteService.buscarPorId(id);
        model.addAttribute("clienteEdit", cliente);  
        return "editarCliente";  
    }

    
    @GetMapping("/eliminar/{id}")
    public String eliminarCliente(@PathVariable Long id, RedirectAttributes redirectAttrs) {
        try {
            clienteService.eliminar(id);
            redirectAttrs.addFlashAttribute("mensaje", "Cliente eliminado correctamente");
            redirectAttrs.addFlashAttribute("tipo", "success");
        } catch (RuntimeException e) {
            // Capturamos el error y lo enviamos como mensaje de SweetAlert
            redirectAttrs.addFlashAttribute("mensaje", e.getMessage());
            redirectAttrs.addFlashAttribute("tipo", "error");
        }

        return "redirect:/clientes"; // Siempre volvemos a la lista
    }

}