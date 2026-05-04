package com.cws.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cws.shop.model.BuyerPurchase;

public interface BuyerPurchaseRepository extends JpaRepository<BuyerPurchase, String> {
	long countByUserId(Long userId);
	
	
	@Query("SELECT COALESCE(SUM(p.amount), 0) FROM BuyerPurchase p WHERE p.userId = :userId")
	double sumAmountByUserId(@Param("userId") Long userId);
}
