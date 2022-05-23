package com.ecom.delivery.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ecom.delivery.domain.DeliveryDetails;


public interface DeliveryRepositoy extends MongoRepository<DeliveryDetails, String>{

}