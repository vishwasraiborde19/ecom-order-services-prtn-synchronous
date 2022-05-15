package com.ecom.product.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.product.domain.ProductCat;
import com.ecom.product.repository.ProductCatRepository;
import com.ecom.product.vo.ProductCatVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductCatService {

	@Autowired
	ProductCatRepository productCatRepository;

	public List<ProductCatVO> getProductsCategories() {
		log.info("fetching products  categories");
		return productCatRepository.findAll().stream().map(this::getValueObject).collect(Collectors.toList());
	}


	private ProductCatVO getValueObject(ProductCat domainObject) {
		ProductCatVO valueObject = new ProductCatVO();
		BeanUtils.copyProperties(domainObject, valueObject);
		return valueObject;
	}

}
