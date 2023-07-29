package com.psja.delivery.service;

import java.util.List;

import com.psja.delivery.controller.request.DeliveryCreationRequest;
import com.psja.delivery.entity.DeliveryInformationEntity;

public interface DeliveryService {

	public void createDeliveryRecord(DeliveryCreationRequest deliveryCreationRequest)throws Exception;
	public List<DeliveryCreationRequest> getDeliveryInformation(String transactionId)throws Exception;
	public void deleteDeliveryInformationUsingTransactionId( String transactionId )throws Exception;
}
