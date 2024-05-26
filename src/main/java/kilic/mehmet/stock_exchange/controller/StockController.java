package kilic.mehmet.stock_exchange.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kilic.mehmet.stock_exchange.entity.Stock;
import kilic.mehmet.stock_exchange.entity.request.StockRequest;
import kilic.mehmet.stock_exchange.service.StockService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/stock")
@RequiredArgsConstructor
public class StockController {
	
	private final StockService stockService;
	
	@GetMapping
	public ResponseEntity<List<Stock>> getAll(){
		return new ResponseEntity<>(stockService.getAll(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Stock> createStock(@RequestBody StockRequest stockProperyRequest){
		return new ResponseEntity<>(stockService.createStock(stockProperyRequest), HttpStatus.OK);
	}
	
	@DeleteMapping
	public ResponseEntity<Boolean> deleteStock(@RequestBody Stock stockPropery){
		return new ResponseEntity<>(stockService.deleteStock(stockPropery), HttpStatus.OK);
	}
	
}
