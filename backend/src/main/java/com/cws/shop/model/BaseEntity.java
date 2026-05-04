package com.cws.shop.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;

public class BaseEntity {
	@CreationTimestamp
	@Column(updatable = false, nullable = false)
	private LocalDateTime createdAt;

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	
}
