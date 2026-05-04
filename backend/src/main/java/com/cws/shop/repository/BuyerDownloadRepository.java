package com.cws.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cws.shop.model.BuyerDownload;

public interface BuyerDownloadRepository extends JpaRepository<BuyerDownload, String>{
	long countByUserId(Long userId);
}
