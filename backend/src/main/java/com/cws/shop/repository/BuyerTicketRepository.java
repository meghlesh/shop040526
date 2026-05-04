package com.cws.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cws.shop.model.BuyerTicket;

public interface BuyerTicketRepository extends JpaRepository<BuyerTicket, String> {
	long countByUserId(Long userId);
}
