package com.ecom.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.product.domain.ProductCat;

@Repository
public interface ProductCatRepository  extends JpaRepository<ProductCat, Long>{
	

}
