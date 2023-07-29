package com.psja.delivery.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.psja.delivery.controller.request.DeliveryCreationRequest;
import com.psja.delivery.response.SuccessResponse;
import com.psja.delivery.service.DeliveryService;

@RestController
@RequestMapping( value = "/delivery" )
public class DeliveryController {
	
	@Autowired
	@Qualifier("BASE_DELIVERY_SERVICE")
	private DeliveryService deliveryService;
	
	@RequestMapping( value = "/create_delivery" )
	public ResponseEntity<String> createDelivery(@RequestBody DeliveryCreationRequest deliveryCreationRequest )throws Exception{
		deliveryService.createDeliveryRecord(deliveryCreationRequest);
		return null;
	}
	
	@RequestMapping( value = "/get_delivery_information", method = RequestMethod.GET )
	public ResponseEntity<List<DeliveryCreationRequest>> retrieveDeliveryInformation(@RequestBody String transactionId)throws Exception{
		return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON)
											.body( deliveryService.getDeliveryInformation( transactionId ) );
	}
	
	@RequestMapping( value = "/cancel_delivery", method = RequestMethod.GET )
	public ResponseEntity<?> cancelOrderUsingTransactionId( @RequestParam("transaction_id")String transactionId )throws Exception{
		
		deliveryService.deleteDeliveryInformationUsingTransactionId(transactionId);
		SuccessResponse successResponse = new SuccessResponse();
		successResponse.setStatus( "SUCCESS" );
		
		return ResponseEntity.status(200).contentType( MediaType.APPLICATION_JSON ).body( successResponse );
	}
}
