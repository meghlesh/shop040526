package com.cws.shop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

@Entity
@Table(name = "downloads", indexes = {
		@Index(name = "idx_download_user", columnList = "user_id")
})
public class BuyerDownload extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	@Column(name = "user_id", nullable = false, length=64)
	private String userId;

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
	
	
	
}
