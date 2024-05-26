package kilic.mehmet.stock_exchange.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import kilic.mehmet.stock_exchange.entity.StockExchange;

@Repository
public interface StockExchangeRepository extends JpaRepository<StockExchange, Integer> {
	
	@Query("SELECT se from StockExchange se where se.name=(:name)")
	Optional<StockExchange> getStockExchangeByName(@Param("name") String name);
	
}
