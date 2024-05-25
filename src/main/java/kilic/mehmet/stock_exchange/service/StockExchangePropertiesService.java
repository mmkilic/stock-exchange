package kilic.mehmet.stock_exchange.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kilic.mehmet.stock_exchange.entity.StockExchangeProperties;
import kilic.mehmet.stock_exchange.entity.StockProperties;
import kilic.mehmet.stock_exchange.exception.BadRequestException;
import kilic.mehmet.stock_exchange.repository.StockExchangePropertiesRepository;
import kilic.mehmet.stock_exchange.repository.StockPropertiesRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StockExchangePropertiesService {
	
	private final StockExchangePropertiesRepository stockExchRepo;
	private final StockPropertiesRepository stockRepo;
	
	public List<StockExchangeProperties> getAll() {
		return stockExchRepo.findAll();
	}
	public StockExchangeProperties getStockExchangeProperties(String name) {
		return stockExchRepo.getStockExchangeProperties(name).orElseThrow(()-> new BadRequestException("Missing Stock Exchange"));
	}
	
	@Transactional
	public StockExchangeProperties addStockProperties(String name, StockProperties stockPropery) {
		var stockexch =  getStockExchangeProperties(name);
		var stock = stockRepo.getStockProperties(stockPropery.getName())
				.orElseThrow(()-> new BadRequestException("Missing Stock! Please record stock first"));;
		
		stockexch.getStockProperties().add(stock);
		
		stock.setStockExchangeProperties(stockexch);
		stockRepo.save(stock);
		
		if(stockexch.getStockProperties().size() >= 5)
			stockexch.setLiveInMarket(true);
		else
			stockexch.setLiveInMarket(false);
		
		return stockExchRepo.save(stockexch);
	}
	
	@Transactional
	public StockExchangeProperties deleteStockProperties(String name, StockProperties stockPropery) {
		var stockexch =  getStockExchangeProperties(name);
		
		boolean stockInStockExchange = false;
		for (StockProperties stock : stockexch.getStockProperties()) {
			if(stock.getName().equals(stockPropery.getName())) {
				stockexch.getStockProperties().remove(stock);
				stockPropery = stock;
				stockInStockExchange = true;
				break;
			}
		}
		
		if(!stockInStockExchange)
			throw new BadRequestException("The StockExchange does not include the stock");
		
		stockPropery.setStockExchangeProperties(null);
		stockRepo.save(stockPropery);
		
		if(stockexch.getStockProperties().size() >= 5)
			stockexch.setLiveInMarket(true);
		else
			stockexch.setLiveInMarket(false);
		
		return stockExchRepo.save(stockexch);
	}
	
}
