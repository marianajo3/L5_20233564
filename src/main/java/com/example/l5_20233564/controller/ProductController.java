package com.example.l5_20233564.controller;

import com.example.l5_20233564.entity.Customer;
import com.example.l5_20233564.entity.Invoice;
import com.example.l5_20233564.entity.Product;
import com.example.l5_20233564.repository.CustomerRepository;
import com.example.l5_20233564.repository.InvoiceRepository;
import com.example.l5_20233564.repository.ProductRepository;
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
public class ProductController {
    @Autowired
    private InvoiceRepository invoiceRepository;
    //@Autowired
    //private CursoRepository cursoRepository;
    //@Autowired
    //private EstudianteRepository estudianteRepository;
    @GetMapping("/invoices/lista")
    public String listarInvoices(Model model) {
        model.addAttribute("invoices", invoiceRepository.findAll());
        return "invoices/lista";
    }

    @GetMapping("/invoices/nuevo")
    public String nuevoInvoices(Model model) {
        model.addAttribute("invoice", new Invoice());
        return "invoices/formulario";
    }

    @PostMapping("/invoices/guardar")
    public String guardarInvoices(@Valid @ModelAttribute Invoice invoice, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "invoices/formulario";
        }
        invoiceRepository.save(invoice);
        redirectAttributes.addFlashAttribute(
                "mensaje",
                "Comprobante guardado correctamente");
        return "redirect:/invoices/lista";
    }

    @GetMapping("/invoices/editar/{id}")
    public String editarInvoices(@PathVariable Integer id, Model model) {
        Invoice invoice=invoiceRepository.findById(id).orElse(null);
        model.addAttribute("invoice",invoice);
        return  "invoices/formulario";
    }
    @GetMapping("/invoices/eliminar/{id}")
    public String eliminarInvoices(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        //matriculaRepository.deleteByCurso_Id(id);
        invoiceRepository.deleteById(id);

        redirectAttributes.addFlashAttribute(
                "mensaje",
                "Comprobante eliminado correctamente");
        return "redirect:/invoices/lista";
    }
}
