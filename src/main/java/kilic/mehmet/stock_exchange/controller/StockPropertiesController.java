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

import kilic.mehmet.stock_exchange.entity.StockProperties;
import kilic.mehmet.stock_exchange.entity.request.StockPropertiesRequest;
import kilic.mehmet.stock_exchange.service.StockPropertiesService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/stock")
@RequiredArgsConstructor
public class StockPropertiesController {
	
	private final StockPropertiesService stockService;
	
	@GetMapping
	public ResponseEntity<List<StockProperties>> getAll(){
		return new ResponseEntity<>(stockService.getAll(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<StockProperties> createStock(@RequestBody StockPropertiesRequest stockProperyRequest){
		return new ResponseEntity<>(stockService.createStock(stockProperyRequest), HttpStatus.OK);
	}
	
	@DeleteMapping
	public ResponseEntity<Boolean> deleteStock(@RequestBody StockProperties stockPropery){
		return new ResponseEntity<>(stockService.deleteStock(stockPropery), HttpStatus.OK);
	}
	
}
