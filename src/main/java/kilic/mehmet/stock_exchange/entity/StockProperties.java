package kilic.mehmet.stock_exchange.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import kilic.mehmet.stock_exchange.entity.request.StockPropertiesRequest;
import lombok.Data;

@Entity
@Table
@Data
public class StockProperties {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(unique = true, nullable = false)
	private String name;
	private String description;
	private double currentPrice;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.nnn")
	private LocalDateTime lastUpdate;
	
	
	
	@JsonIgnore
	@ManyToOne
	private StockExchangeProperties stockExchangeProperties;
	
	public StockProperties update(StockPropertiesRequest spr) {
		this.name = spr.getName();
		this.description = spr.getDescription();
		this.currentPrice = spr.getCurrentPrice();
		this.lastUpdate = LocalDateTime.now();
		return this;
	}
	
}

