package com.example.l5_20233564.controller;

import com.example.l5_20233564.entity.Customer;
import com.example.l5_20233564.repository.CustomerRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/customers/lista")
    public String listarCustomers(Model model) {
        model.addAttribute("customers", customerRepository.findAll());
        return "customers/lista";
    }

    @GetMapping("/customers/nuevo")
    public String nuevoCustomer(Model model) {
        model.addAttribute("customer", new Customer());
        return "customers/formulario";
    }

    @PostMapping("/customers/guardar")
    public String guardarCustomers(@Valid @ModelAttribute Customer customer, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "customers/formulario";
        }
        customerRepository.save(customer);
        redirectAttributes.addFlashAttribute(
                "mensaje",
                "Cliente guardado correctamente");
        return "redirect:/customers/lista";
    }

    @GetMapping("/customers/editar/{id}")
    public String editarCustomer(@PathVariable Integer id, Model model) {
        Customer customer=customerRepository.findById(id).orElse(null);
        model.addAttribute("customer",customer);
        return  "customers/formulario";
    }
    @GetMapping("/customers/eliminar/{id}")
    public String eliminarCustomers(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        //matriculaRepository.deleteByCurso_Id(id);
        customerRepository.deleteById(id);

        redirectAttributes.addFlashAttribute(
                "mensaje",
                "Cliente eliminado correctamente");
        return "redirect:/customers/lista";
    }
}