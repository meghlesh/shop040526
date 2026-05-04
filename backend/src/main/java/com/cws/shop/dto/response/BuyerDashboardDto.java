package com.cws.shop.dto.response;

public class BuyerDashboardDto {
	long totalPurchase;
	long totalDownload;
	long totaltickets;
	double totalMoney;
	
	public BuyerDashboardDto(long totalPurchase, long totalDownload, long totaltickets, double totalMoney) {
		this.totalPurchase = totalPurchase;
		this.totalDownload = totalDownload;
		this.totaltickets = totaltickets;
		this.totalMoney = totalMoney;
	}
	public long getTotalPurchase() {
		return totalPurchase;
	}
	public void setTotalPurchase(long totalPurchase) {
		this.totalPurchase = totalPurchase;
	}
	public long getTotalDownload() {
		return totalDownload;
	}
	public void setTotalDownload(long totalDownload) {
		this.totalDownload = totalDownload;
	}
	public long getTotaltickets() {
		return totaltickets;
	}
	public void setTotaltickets(long totaltickets) {
		this.totaltickets = totaltickets;
	}
	public double getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(double totalMoney) {
		this.totalMoney = totalMoney;
	}
	
	

}
