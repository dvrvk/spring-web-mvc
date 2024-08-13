package com.example.controller;

import com.example.entity.Product;
import com.example.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {
    // PUEDO USAR: @Autowired o contructor (más recomendable) para insertarlo
    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // MODEL ES UNA CLASE QUE PERMITE CARGAR LOS DATOS A LA VISTA DEL MODELO
    // AL NO ESPECIFICAR NADA EN @GETMAPPING SE HACE REFERENCIA A LA URL DE @REQUESTMAPPING
    @GetMapping
    public String findAll(Model model) {
        // Variable product
        List<Product> products = this.productRepository.findAll();
        model.addAttribute("products", products);

        // DEVOLVER EL TEMPLATE - NOMBRE DEL TEMPLATE
        return "product-list";
    }

    // AL ACCEDER A /oroducts/new DEVUELVE UN FORMULARIO
    @GetMapping("/new")
    public String getForm(Model model) {
        // Cargo un producto vacio
        model.addAttribute("product", new Product());
        return "product-form";
    }

    // ENTRARIA EN EL METODO POST DE /products
    // Recibo con un @ModelAttribute un producto del formulario
    @PostMapping
    public String save(@ModelAttribute("product") Product product) {

        // Validar el producto y guardar en la base de datos
        this.productRepository.save(product);

        // Redirigir al listado de productos
        return "redirect:/products";
    }

    // Entrada para /products/{id}/view
    @GetMapping("/{id}/view")
    public String view(@PathVariable("id") Long id, Model model) {
        Product product = this.productRepository.findById(id).orElse(null);
        model.addAttribute("product", product);
        return "product-view";
    }

    // METODO DE UPDATE GET /products/{id}/edit
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model) {
        Product product = this.productRepository.findById(id).orElse(null);
        model.addAttribute("product", product);
        return "product-edit";
    }

    // METODO DELETE /products/{id}/delete
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        this.productRepository.deleteById(id);
        return "redirect:/products";
    }

    // METODO DETELEALL /products/delete/all
    @GetMapping("/delete/all")
    public String deleteAll() {
        this.productRepository.deleteAll();
        return "redirect:/products";
    }

}
