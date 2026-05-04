package com.cws.shop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cws.shop.dto.response.ApiResponse;
import com.cws.shop.dto.response.BuyerDashboardDto;
import com.cws.shop.service.BuyerDashboardService;

@RestController
@RequestMapping("/buyer")
public class BuyerDashboardController {
	private final BuyerDashboardService buyerDashboardServive;

	public BuyerDashboardController(BuyerDashboardService buyerDashboardServive) {
		this.buyerDashboardServive = buyerDashboardServive;
	}
	
	@GetMapping("/dashboard-stats")
	public ApiResponse<BuyerDashboardDto> getDashboard() {
		BuyerDashboardDto buyerDashboardDto = buyerDashboardServive.getDashboardForCurrentUser();
		
		return new ApiResponse<>(
                true,
                "Dashboard fetched successfully",
                buyerDashboardDto
        );
	}
	
	
}
