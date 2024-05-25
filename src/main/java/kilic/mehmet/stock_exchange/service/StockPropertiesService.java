package kilic.mehmet.stock_exchange.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kilic.mehmet.stock_exchange.entity.StockProperties;
import kilic.mehmet.stock_exchange.entity.request.StockPropertiesRequest;
import kilic.mehmet.stock_exchange.exception.BadRequestException;
import kilic.mehmet.stock_exchange.repository.StockPropertiesRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StockPropertiesService {
	
	private final StockPropertiesRepository stockRepo;
	
	public List<StockProperties> getAll() {
		return stockRepo.findAll();
	}
	
	@Transactional
	public StockProperties createStock(StockPropertiesRequest stockProperyRequest) {
		return stockRepo.save(new StockProperties().update(stockProperyRequest));
	}
	
	@Transactional
	public boolean deleteStock(StockProperties stockPropery) {
		stockPropery = stockRepo.getStockProperties(stockPropery.getName())
				.orElseThrow(()-> new BadRequestException("Missing Stock"));
		
		if(stockPropery.getStockExchangeProperties() == null)
			throw new BadRequestException("The Stock must be delisted from StockExchange first");
		
		stockRepo.delete(stockPropery);
		return true;
	}
	
}
