package com.ecom.delivery.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.ecom.delivery.domain.DeliveryDetails;


public interface DeliveryRepositoy extends MongoRepository<DeliveryDetails, String>{

	@Query("{ 'postCode' : ?0 }")
	List<DeliveryDetails> findByPostCode(String postCode);

}