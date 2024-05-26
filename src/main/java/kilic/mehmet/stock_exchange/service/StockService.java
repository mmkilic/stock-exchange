package kilic.mehmet.stock_exchange.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kilic.mehmet.stock_exchange.entity.Stock;
import kilic.mehmet.stock_exchange.entity.request.StockRequest;
import kilic.mehmet.stock_exchange.exception.BadRequestException;
import kilic.mehmet.stock_exchange.repository.StockRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StockService {
	
	private final StockRepository stockRepo;
	
	public List<Stock> getAll() {
		return stockRepo.findAll();
	}
	
	@Transactional
	public Stock createStock(StockRequest stockRequest) {
		return stockRepo.save(new Stock().update(stockRequest));
	}
	
	@Transactional
	public Stock updateStock(Stock stockInput) {
		var stock = stockRepo.getStockByName(stockInput.getName())
				.orElseThrow(()-> new BadRequestException("Missing Stock"));
		stock.setCurrentPrice(stockInput.getCurrentPrice());
		stock.setLastUpdate(LocalDateTime.now());
		return stockRepo.save(stock);
	}
	
	@Transactional
	public boolean deleteStock(Stock stock) {
		stock = stockRepo.getStockByName(stock.getName())
				.orElseThrow(()-> new BadRequestException("Missing Stock"));
		
		if(stock.getStockExchange() == null)
			throw new BadRequestException("The Stock must be delisted from StockExchange first");
		
		stockRepo.delete(stock);
		return true;
	}
	
}
