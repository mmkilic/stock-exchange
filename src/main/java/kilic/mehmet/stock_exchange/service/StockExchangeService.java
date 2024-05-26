package kilic.mehmet.stock_exchange.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kilic.mehmet.stock_exchange.entity.StockExchange;
import kilic.mehmet.stock_exchange.entity.Stock;
import kilic.mehmet.stock_exchange.exception.BadRequestException;
import kilic.mehmet.stock_exchange.repository.StockExchangeRepository;
import kilic.mehmet.stock_exchange.repository.StockRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StockExchangeService {
	
	private final StockExchangeRepository stockExchRepo;
	private final StockRepository stockRepo;
	
	public List<StockExchange> getAll() {
		return stockExchRepo.findAll();
	}
	public StockExchange getStockExchangeByName(String name) {
		return stockExchRepo.getStockExchangeByName(name)
				.orElseThrow(()-> new BadRequestException("Missing Stock Exchange"));
	}
	
	@Transactional
	public StockExchange addStockByName(String name, Stock stockInput) {
		var stockexch =  getStockExchangeByName(name);
		var stock = stockRepo.getStockByName(stockInput.getName())
				.orElseThrow(()-> new BadRequestException("Missing Stock! Please record stock first"));;
		
		stockexch.getStocks().add(stock);
		
		stock.setStockExchange(stockexch);
		stockRepo.save(stock);
		
		if(stockexch.getStocks().size() >= 5)
			stockexch.setLiveInMarket(true);
		else
			stockexch.setLiveInMarket(false);
		
		return stockExchRepo.save(stockexch);
	}
	
	@Transactional
	public StockExchange deleteStockByName(String name, Stock stockInput) {
		var stockexch =  getStockExchangeByName(name);
		
		boolean stockInStockExchange = false;
		for (Stock stock : stockexch.getStocks()) {
			if(stock.getName().equals(stockInput.getName())) {
				stockexch.getStocks().remove(stock);
				stockInput = stock;
				stockInStockExchange = true;
				break;
			}
		}
		
		if(!stockInStockExchange)
			throw new BadRequestException("The StockExchange does not include the stock");
		
		stockInput.setStockExchange(null);
		stockRepo.save(stockInput);
		
		if(stockexch.getStocks().size() >= 5)
			stockexch.setLiveInMarket(true);
		else
			stockexch.setLiveInMarket(false);
		
		return stockExchRepo.save(stockexch);
	}
	
}
