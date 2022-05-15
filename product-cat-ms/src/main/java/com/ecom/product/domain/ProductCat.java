package com.ecom.product.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "productCategory")
public class ProductCat implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer catId;
	
	@Column(name = "productLine")
	private String productLine;
	
	@Column(name = "textDescription")
	private String textDescription;
	
	@Column(name = "htmlDescription")
	private String htmlDescription;
	
	private String image;
	
}
