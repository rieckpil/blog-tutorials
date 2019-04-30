package de.rieckpil.blog.entity;

import java.util.Date;
import java.util.UUID;

public class Order {

	private String orderId;
	private String taxId;
	private String customerId;
	private String product;
	private Integer amount;
	private Date orderDate;
	private boolean expressDelivery;

	public Order() {
		this.orderId = UUID.randomUUID().toString();
	}

	public Order(String taxId, String customerId, String product, Integer amount, Date orderDate,
			boolean expressDelivery) {
		super();
		this.orderId = UUID.randomUUID().toString();
		this.taxId = taxId;
		this.customerId = customerId;
		this.product = product;
		this.amount = amount;
		this.orderDate = orderDate;
		this.expressDelivery = expressDelivery;
	}

	public String getOrderId() {
		return orderId;
	}

	public String getTaxId() {
		return taxId;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public boolean isExpressDelivery() {
		return expressDelivery;
	}

	public void setExpressDelivery(boolean expressDelivery) {
		this.expressDelivery = expressDelivery;
	}

}
