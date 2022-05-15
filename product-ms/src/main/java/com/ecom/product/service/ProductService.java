package com.ecom.product.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.product.domain.Product;
import com.ecom.product.repository.ProductRepository;
import com.ecom.product.vo.ProductVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public List<ProductVO> productService() {

		return productRepository.findAll().stream().map(
		        this::getValueObject).collect(Collectors.toList());

	}

	public Optional<Product> getProduct(String productCode) {
		return productRepository.findById(productCode);
	}

	public List<ProductVO> getProductsByCategory(Integer catId) {
		return productRepository.getProductsByCategory(catId).stream().map(
		        this::getValueObject).collect(Collectors.toList());
	}

	public ProductVO addProduct(ProductVO product) {
		log.info(product.toString());
		Product productDomain = productRepository.save(getDomainObject(product));
		return getValueObject(productDomain);
	}

	public Product deleteProduct(String productCode) {
		Product prd = new Product();
		prd.setProductCode(productCode);
		productRepository.delete(prd);
		return prd;
	}

	private ProductVO getValueObject(Product domainObject) {
		ProductVO valueObject = new ProductVO();
		BeanUtils.copyProperties(domainObject, valueObject);
		return valueObject;
	}
	private Product getDomainObject(ProductVO valueObject) {
		Product domian = new Product();
		BeanUtils.copyProperties(valueObject,domian);
		return domian;
	}

}
