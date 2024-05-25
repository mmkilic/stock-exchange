package kilic.mehmet.stock_exchange.entity;

import java.util.List;
import java.util.Vector;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class StockExchangeProperties {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(unique = true, nullable = false)
	private String name;
	private String description;
	private boolean liveInMarket;
	
	@OneToMany(mappedBy = "stockExchangeProperties")
	private List<StockProperties> stockProperties = new Vector<StockProperties>(); 
	
}