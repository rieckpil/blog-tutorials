package de.rieckpil.blog;

import java.time.Instant;

import javax.json.JsonObject;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class StockHistory {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String stockCode;

	@Column(nullable = false)
	private Double price;

	@Column(nullable = false)
	private Instant timestamp;

	public StockHistory() {

	}

	public StockHistory(JsonObject json) {
		this.stockCode = json.getString("stockCode");
		this.price = json.getJsonNumber("price").doubleValue();
		this.timestamp = Instant.ofEpochMilli(json.getJsonNumber("timestamp").longValue());
	}

	public StockHistory(String stockCode, Double price, Instant timestamp) {
		super();
		this.stockCode = stockCode;
		this.price = price;
		this.timestamp = timestamp;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Instant getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "StockHistory [id=" + id + ", stockCode=" + stockCode + ", price=" + price + ", timestamp=" + timestamp
				+ "]";
	}

}
