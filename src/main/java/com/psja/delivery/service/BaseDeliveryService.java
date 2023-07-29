package com.psja.delivery.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.psja.delivery.assembler.DeliveryAssembler;
import com.psja.delivery.common.SystemException;
import com.psja.delivery.controller.request.DeliveryCreationRequest;
import com.psja.delivery.entity.DeliveryInformationEntity;

@Service("BASE_DELIVERY_SERVICE")
public class BaseDeliveryService implements DeliveryService{

	@Autowired
	private DeliveryAssembler deliveryAssembler;
	
	private void prevalidateBeforeSave(DeliveryCreationRequest deliveryCreationRequest)throws Exception {
		if ( deliveryCreationRequest.getTransactionId() == null )
			throw new SystemException( new Exception("TRANSACTION_ID_NOT_PRESENT") );
	} 
	
	@Override
	public void createDeliveryRecord(DeliveryCreationRequest deliveryCreationRequest)throws Exception {
		System.out.println("Inserting into createDeliveryRecord");
		prevalidateBeforeSave( deliveryCreationRequest );
		Transaction tx = null;
		try {

			DeliveryInformationEntity deliveryInformationEntity = deliveryAssembler
					.toDomainObj(deliveryCreationRequest);

			Configuration con = new Configuration().configure().addAnnotatedClass(DeliveryInformationEntity.class);
			Session session = con.buildSessionFactory().openSession();
			tx = session.beginTransaction();
			session.persist(deliveryInformationEntity);
			//throw new SystemException(new Exception("TEXT_EXCEPTION"));
			tx.commit();

		} catch(Exception exp) {
			System.out.println("Entering the catch block");
			tx.rollback();
			System.out.println("Exiting the catch block");
			throw exp;
		}
	}
	
	private void prevalidateBeforeGetDeliveryInformation( String transactionId )throws Exception {
		System.out.println( "Inserting into prevalidateBeforeGetDeliveryInformation" );
		if ( transactionId == null )
			throw new SystemException( new Exception("TRANSACTION_ID_NOT_PRESENT") );
		System.out.println( "Exiting from prevalidateBeforeGetDeliveryInformation" );
	}
	
	
	private List<DeliveryInformationEntity> persistForGetDeliveryInformation( String transactionId )throws Exception {
		
		System.out.println("Inserting into persistForGetDeliveryInformation");
		List<DeliveryInformationEntity> resultList = null;
		try {
			Configuration configuration = new Configuration().configure()
										.addAnnotatedClass(DeliveryInformationEntity.class);
	
			Session session = configuration.buildSessionFactory().openSession();
			
			String queryString = "select * from delivery_information d where d.transaction_id='"+transactionId+"'"; 
			Query<DeliveryInformationEntity> query = session.createNativeQuery(queryString, DeliveryInformationEntity.class);
			resultList= query.getResultList();
		} catch( Exception exp ) {
			throw new SystemException( new Exception("ERROR_IN_RETRIEVING_RECORD") );
		}
		System.out.println( "Exiting from persistForGetDeliveryInformation" );
		return resultList;
	}
	@Override
	public List<DeliveryCreationRequest> getDeliveryInformation(String transactionId)throws Exception{
		
		System.out.println("Inserting into getDeliveryInformation");
		prevalidateBeforeGetDeliveryInformation( transactionId );
		List<DeliveryInformationEntity> resultList = persistForGetDeliveryInformation( transactionId );
		System.out.println("Exiting from getDeliveryInformation");
		List<DeliveryCreationRequest> deliveryCreationRequestList = new ArrayList<>();
		resultList.forEach( data->{
			deliveryCreationRequestList.add( deliveryAssembler.fromDomainObj(data) );
		} );
		return deliveryCreationRequestList;
	}
	
	private void prevalidateBeforeDeleteBeforeDeliveryInformationUsingTransactionId( String transactionId )throws Exception {
		if ( transactionId == null )
			throw new SystemException( new Exception("TRANSACTION_ID_NOT_GIVEN") );
	}
	
	private void persistDeleteByTransactionId( String transactionId )throws Exception {
		System.out.println( "Inserting into persistDeleteByTransactionId" );
		Configuration con = new Configuration().configure().addAnnotatedClass( getClass());
		Session session = con.buildSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			
			//tx.begin();
			Query query = session.createNativeQuery( "delete from delivery_information where transaction_id = '"+transactionId+"'" );
			query.executeUpdate();
			tx.commit();
		} catch( Exception exp ) {
			
				tx.rollback();
			throw new SystemException(exp);
			
		}
		System.out.println( "Exiting from persistDeleteByTransactionId" );
	}
	
	@Override
	public void deleteDeliveryInformationUsingTransactionId( String transactionId )throws Exception{
		System.out.println( "Inserting into deleteDeliveryInformationUsingTransactionId" );
		
		prevalidateBeforeDeleteBeforeDeliveryInformationUsingTransactionId( transactionId );
		persistDeleteByTransactionId( transactionId );
		System.out.println( "Exiting from deleteDeliveryInformationUsingTransactionId" );
	}
}
