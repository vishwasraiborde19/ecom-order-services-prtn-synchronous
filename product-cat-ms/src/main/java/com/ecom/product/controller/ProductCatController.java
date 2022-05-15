package com.ecom.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ecom.product.service.ProductCatService;
import com.ecom.product.vo.ProductCatVO;
import com.ecom.product.vo.ProductVO;

@RestController()
@RequestMapping("/cats")
public class ProductCatController {

	@Autowired
	ProductCatService productCatService;

	@Autowired
	RestTemplate restTemplate;

	@GetMapping
	public List<ProductCatVO> getProductsCategories() {
		return productCatService.getProductsCategories();
	}

	@GetMapping("/{catId}/products")
	public List<ProductVO> getProductsCategories(@PathVariable Integer catId) {
		return restTemplate.getForObject("http://PRODUCT-SERVICE/products/" + catId, List.class);

	}

}
