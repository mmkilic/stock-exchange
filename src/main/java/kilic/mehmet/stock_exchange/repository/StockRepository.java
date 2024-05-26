package kilic.mehmet.stock_exchange.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import kilic.mehmet.stock_exchange.entity.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {
	
	@Query("SELECT s from Stock s where s.name=(:name)")
	Optional<Stock> getStockByName(@Param("name") String name);
}
