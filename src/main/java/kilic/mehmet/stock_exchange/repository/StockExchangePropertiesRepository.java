package kilic.mehmet.stock_exchange.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import kilic.mehmet.stock_exchange.entity.StockExchangeProperties;

@Repository
public interface StockExchangePropertiesRepository extends JpaRepository<StockExchangeProperties, Integer> {
	
	@Query("SELECT se from StockExchangeProperties se where se.name=(:name)")
	Optional<StockExchangeProperties> getStockExchangeProperties(@Param("name") String name);
	
}
