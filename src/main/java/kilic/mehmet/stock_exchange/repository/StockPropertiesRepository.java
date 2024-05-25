package kilic.mehmet.stock_exchange.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import kilic.mehmet.stock_exchange.entity.StockProperties;

@Repository
public interface StockPropertiesRepository extends JpaRepository<StockProperties, Integer> {
	
	@Query("SELECT s from StockProperties s where s.name=(:name)")
	Optional<StockProperties> getStockProperties(@Param("name") String name);
}
