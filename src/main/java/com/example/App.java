package com.example;

import com.example.controller.ProductController;
import com.example.entity.Product;
import com.example.repository.ProductRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(App.class, args);

		// Instancia de ProductRepository
		var productRepository = context.getBean(ProductRepository.class);

		// INSERTO DATOS DEMO
		List<Product> products = List.of(
				new Product(null, "producto1", 5.99,1),
				new Product(null, "producto2", 19.99,2),
				new Product(null, "producto3", 8.99,10),
				new Product(null, "producto4", 15.99,20),
				new Product(null, "producto5", 5.80,19)
		);

		// Creo una lista de productos y se guarda en la base de datos H2 embebida en memoria
		productRepository.saveAll(products);
	}

}
