package com.ecom.delivery.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.delivery.domain.DeliveryDetails;
import com.ecom.delivery.repository.DeliveryRepositoy;
import com.ecom.delivery.vo.DeliveryDetailsVO;

@Service
public class DeliveryService {

	@Autowired
	private DeliveryRepositoy deliveryRepo;

	public DeliveryDetailsVO addDelivery(DeliveryDetails deliveryDetails) {

		DeliveryDetails savedDetails = deliveryRepo.save(deliveryDetails);
		DeliveryDetailsVO vo = new DeliveryDetailsVO();
		BeanUtils.copyProperties(savedDetails, vo);

		return vo;

	}

	public List<DeliveryDetailsVO> getAll() {

		return deliveryRepo.findAll().stream().map(this::getValue).collect(Collectors.toList());

	}
	
	public List<DeliveryDetailsVO> findByPostCode(String postCode) {
		List< DeliveryDetails> deliveryDetails = deliveryRepo.findByPostCode(postCode);
		
		 System.out.println(deliveryDetails.toString());
		
		 List<DeliveryDetailsVO> DeliveryDetailsVOs= deliveryDetails.stream().map(this::getValue).collect(Collectors.toList());
		 
		 System.out.println(DeliveryDetailsVOs.toString());
		 
		 return DeliveryDetailsVOs;

	}

	
	
	public DeliveryDetailsVO findById(String id) {
		
		 Optional<DeliveryDetails> deliveryDetail = deliveryRepo.findById(id);
		 if (deliveryDetail.isPresent()) {
			 return getValue(deliveryDetail.get());
		 }else {
			 Optional.empty();
		 }
		return null;
		

	}

	private DeliveryDetailsVO getValue(DeliveryDetails deliveryDetails) {

		DeliveryDetailsVO vo = new DeliveryDetailsVO();
		BeanUtils.copyProperties(deliveryDetails, vo);

		return vo;

	}

}
