package com.cws.shop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

@Entity
@Table(name= "purchases", indexes = {
		@Index(name = "idx_purchase_user", columnList ="user_id")
})
public class BuyerPurchase extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	@Column(name = "user_id", nullable = false, length=64)
	private String userId;
	
	@Column(nullable = false)
	private double amount;

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
