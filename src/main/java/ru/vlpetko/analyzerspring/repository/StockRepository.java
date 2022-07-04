package ru.vlpetko.analyzerspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.vlpetko.analyzerspring.model.Stock;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    @Query("select new Stock(s.stockName, s.reportNumber, s.uploadDate) from Stock s group by s.reportNumber, s.stockName, s.uploadDate order by s.reportNumber ")
    List<Stock> findAllDistinctByReportNumber();

    List<Stock> findStocksByStockName(String stockName);

    List<Stock> findAll();

    @Query("select new Stock(s.stockName, s.reportNumber, s.uploadDate) from Stock s group by s.reportNumber, s.stockName, s.uploadDate order by s.reportNumber")
    List<Stock> getDistinctByReportNumber();

    List<Stock> getAllByReportNumber(Integer repNumber);
}
