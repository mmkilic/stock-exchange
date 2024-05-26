package kilic.mehmet.stock_exchange.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kilic.mehmet.stock_exchange.entity.StockExchange;
import kilic.mehmet.stock_exchange.entity.Stock;
import kilic.mehmet.stock_exchange.service.StockExchangeService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/stock-exchange")
@RequiredArgsConstructor
public class StockExchangeController {
	
	private final StockExchangeService stockExchService;
	
	@GetMapping
	public ResponseEntity<List<StockExchange>> getAll(){
		return new ResponseEntity<>(stockExchService.getAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{name}")
	public ResponseEntity<StockExchange> getStockExchangeByName(@PathVariable String name){
		return new ResponseEntity<>(stockExchService.getStockExchangeByName(name), HttpStatus.OK);
	}
	
	@PutMapping("/{name}")
	public ResponseEntity<StockExchange> addStockByName(
			@PathVariable String name,
			@RequestBody Stock stockPropery){
		return new ResponseEntity<>(stockExchService.addStockByName(name, stockPropery), HttpStatus.OK);
	}
	
	@DeleteMapping("/{name}")
	public ResponseEntity<StockExchange> deleteStockByName(
			@PathVariable String name,
			@RequestBody Stock stockPropery){
		return new ResponseEntity<>(stockExchService.deleteStockByName(name, stockPropery), HttpStatus.OK);
	}
	
}
