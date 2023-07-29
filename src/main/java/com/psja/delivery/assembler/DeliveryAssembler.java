package com.psja.delivery.assembler;

import org.springframework.stereotype.Component;

import com.psja.delivery.controller.request.DeliveryCreationRequest;
import com.psja.delivery.entity.DeliveryInformationEntity;

@Component
public class DeliveryAssembler {

	public DeliveryInformationEntity toDomainObj( DeliveryCreationRequest deliveryCreationRequest ) {
		System.out.println("Inserting into toDomainObj");
		
		DeliveryInformationEntity deliveryInformationEntity = new DeliveryInformationEntity();
		deliveryInformationEntity.setTransactionId( deliveryCreationRequest.getTransactionId() );
		deliveryInformationEntity.setDeliveryPartnerName( deliveryCreationRequest.getDeliveryPartnerName() );
		deliveryInformationEntity.setGoodsType( deliveryCreationRequest.getGoodsType() );
		
		System.out.println( "Exiting from toDomainObj" );
		return deliveryInformationEntity;
	}
	
	public DeliveryCreationRequest fromDomainObj( DeliveryInformationEntity deliveryInformationEntity  ) {
		System.out.println( "Inserting into fromDomainObj" );
		
		DeliveryCreationRequest deliveryCreationRequest = new DeliveryCreationRequest();
		deliveryCreationRequest.setDeliveryPartnerName( deliveryInformationEntity.getDeliveryPartnerName() );
		deliveryCreationRequest.setTransactionId( deliveryInformationEntity.getTransactionId() );
		deliveryCreationRequest.setGoodsType( deliveryInformationEntity.getGoodsType() );
		System.out.println( "Exiting from fromDomainObj" );
		return deliveryCreationRequest;
	}
}
