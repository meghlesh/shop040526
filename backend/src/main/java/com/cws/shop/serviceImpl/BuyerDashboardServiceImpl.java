package com.cws.shop.serviceImpl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.cws.shop.dto.response.BuyerDashboardDto;
import com.cws.shop.model.User;
import com.cws.shop.repository.BuyerDownloadRepository;
import com.cws.shop.repository.BuyerPurchaseRepository;
import com.cws.shop.repository.BuyerTicketRepository;
import com.cws.shop.service.BuyerDashboardService;

@Service
public class BuyerDashboardServiceImpl implements BuyerDashboardService{
	private final BuyerPurchaseRepository buyerPurchaseRepository;
	private final BuyerDownloadRepository buyerDownloadRepository;
	private final BuyerTicketRepository buyerTicketRepository;
	
	
	public BuyerDashboardServiceImpl(BuyerPurchaseRepository buyerPurchaseRepository,
			BuyerDownloadRepository buyerDownloadRepository, BuyerTicketRepository buyerTicketRepository) {
		this.buyerPurchaseRepository = buyerPurchaseRepository;
		this.buyerDownloadRepository = buyerDownloadRepository;
		this.buyerTicketRepository = buyerTicketRepository;
	}


	@Override
	public BuyerDashboardDto getDashboardForCurrentUser() {
		//String userId = SecurityUtils.getCurrentUserId();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();   // comes from your JWT filter

        Long userId = user.getId();
		long totalPurchase = buyerPurchaseRepository.countByUserId(userId);
		long totalDownload = buyerDownloadRepository.countByUserId(userId);
		long totalTickets = buyerTicketRepository.countByUserId(userId);
		double totalMoney = buyerPurchaseRepository.sumAmountByUserId(userId);
		
		
		return new BuyerDashboardDto(
				totalPurchase,
				totalDownload,
				totalTickets,
				totalMoney);
	}

}
