package com.example.l5_20233564.controller;

import com.example.l5_20233564.entity.Customer;
import com.example.l5_20233564.entity.Product;
import com.example.l5_20233564.repository.CustomerRepository;
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
    private ProductRepository productRepository;

    @GetMapping("/products/lista")
    public String listarProducts(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "products/lista";
    }

    @GetMapping("/products/nuevo")
    public String nuevoProduct(Model model) {
        model.addAttribute("product", new Product());
        return "products/formulario";
    }

    @PostMapping("/products/guardar")
    public String guardarProducts(@Valid @ModelAttribute Product product, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "products/formulario";
        }
        productRepository.save(product);
        redirectAttributes.addFlashAttribute(
                "mensaje",
                "Producto guardado correctamente");
        return "redirect:/products/lista";
    }

    @GetMapping("/products/editar/{id}")
    public String editarProducts(@PathVariable Integer id, Model model) {
        Product product=productRepository.findById(id).orElse(null);
        model.addAttribute("product",product);
        return  "products/formulario";
    }
    @GetMapping("/products/eliminar/{id}")
    public String eliminarProducts(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        //matriculaRepository.deleteByCurso_Id(id);
        productRepository.deleteById(id);

        redirectAttributes.addFlashAttribute(
                "mensaje",
                "Producto eliminado correctamente");
        return "redirect:/products/lista";
    }
}
