package com.cws.shop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

@Entity
@Table(name = "tickets", indexes = {
		@Index(name ="idx_ticket_user", columnList = "user_id")
})
public class BuyerTicket {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	@Column(name = "user_id", nullable = false, length=64)
	private String userId;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private BuyerTicketStatus status;
}
