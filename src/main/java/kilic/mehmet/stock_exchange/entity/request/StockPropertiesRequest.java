package kilic.mehmet.stock_exchange.entity.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockPropertiesRequest {
	private String name;
	private String description;
	private double currentPrice;
}

