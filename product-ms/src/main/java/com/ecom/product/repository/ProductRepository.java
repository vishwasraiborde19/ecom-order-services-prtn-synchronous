package com.ecom.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecom.product.domain.Product;

@Repository
public interface ProductRepository  extends JpaRepository<Product, String>{
	
	@Query(value = "select * from products where catId=?", nativeQuery=true)
	public List<Product> getProductsByCategory(Integer catId);
	
}
