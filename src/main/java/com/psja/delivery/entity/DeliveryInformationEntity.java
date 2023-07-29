package com.psja.delivery.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table( name = "DELIVERY_INFORMATION" )
public class DeliveryInformationEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(name = "transaction_id")
	private String transactionId;
	@Column(name = "delivery_partner_name")
	private String deliveryPartnerName;
	@Column(name = "goods_type")
	private String goodsType;
	
	public Integer getId() {
		return this.id;
	}
	public void setId( Integer id ) {
		this.id = id;
	}
	
	public String getTransactionId() {
		return this.transactionId;
	}
	public void setTransactionId( String transactionId ) {
		this.transactionId = transactionId;
	}
	
	public String getDeliveryPartnerName() {
		return this.deliveryPartnerName;
	}
	public void setDeliveryPartnerName( String deliveryPartnerName ) {
		this.deliveryPartnerName = deliveryPartnerName;
	}
	
	public String getGoodsType() {
		return this.goodsType;
	}
	public void setGoodsType( String goodsType ) {
		this.goodsType = goodsType;
	}
}
