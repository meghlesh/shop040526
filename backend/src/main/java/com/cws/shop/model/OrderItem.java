package com.cws.shop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;
    private double price;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Order order;
    // ✅ GETTERS
    public Long getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public Product getProduct() {
        return product;
    }

    public Order getOrder() {
        return order;
    }

	public void setOrder(Order order2) {
		this.order = order;
		
	}

	public void setProduct(Product product2) {
		this.product = product;
		
	}

	public void setQuantity(int quantity2) {
		this.quantity = quantity;
		
	}

	public void setPrice(double priceAtTime) {
		 this.price = price;
		
	}
    
}
