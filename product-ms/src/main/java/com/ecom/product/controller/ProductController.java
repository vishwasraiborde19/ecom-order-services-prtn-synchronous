package com.ecom.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.product.service.ProductService;
import com.ecom.product.vo.ProductVO;

@RestController()
@RequestMapping("/products")
public class ProductController {
	
	@Autowired  
	private ProductService productService; 
		
	@GetMapping
	public List<ProductVO> getProducts() {
		return productService.productService();
	}
	
	@GetMapping("/{catId}")
	public List<ProductVO> getProductsByCategory(@PathVariable Integer catId) {
		return productService.getProductsByCategory(catId);
	}
	
	@PostMapping("/")
	public ProductVO addProduct(@RequestBody ProductVO product) {
		return productService.addProduct(product);
	}

}
