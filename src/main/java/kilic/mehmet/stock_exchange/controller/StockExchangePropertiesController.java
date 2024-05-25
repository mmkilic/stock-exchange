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

import kilic.mehmet.stock_exchange.entity.StockExchangeProperties;
import kilic.mehmet.stock_exchange.entity.StockProperties;
import kilic.mehmet.stock_exchange.service.StockExchangePropertiesService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/stock-exchange")
@RequiredArgsConstructor
public class StockExchangePropertiesController {
	
	private final StockExchangePropertiesService stockExchService;
	
	@GetMapping
	public ResponseEntity<List<StockExchangeProperties>> getAll(){
		return new ResponseEntity<>(stockExchService.getAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{name}")
	public ResponseEntity<StockExchangeProperties> getStockExchangeProperties(@PathVariable String name){
		return new ResponseEntity<>(stockExchService.getStockExchangeProperties(name), HttpStatus.OK);
	}
	
	@PutMapping("/{name}")
	public ResponseEntity<StockExchangeProperties> addStockProperties(
			@PathVariable String name,
			@RequestBody StockProperties stockPropery){
		return new ResponseEntity<>(stockExchService.addStockProperties(name, stockPropery), HttpStatus.OK);
	}
	
	@DeleteMapping("/{name}")
	public ResponseEntity<StockExchangeProperties> deleteStockProperties(
			@PathVariable String name,
			@RequestBody StockProperties stockPropery){
		return new ResponseEntity<>(stockExchService.deleteStockProperties(name, stockPropery), HttpStatus.OK);
	}
	
}
