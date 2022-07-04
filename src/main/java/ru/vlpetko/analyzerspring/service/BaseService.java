package ru.vlpetko.analyzerspring.service;

import ru.vlpetko.analyzerspring.model.Stock;

import java.util.List;

public interface BaseService {

    Stock saveStock(Stock stock);

    void deleteStock(Long stockId);

    Stock redactStock(Stock stock);

    void uploadFile(String path);

    Stock getStockById(Long stockId);

    List<Stock> getAllStocks();

    List<Stock> getStockByTicker(String stockName);

    List<Stock> getReports();

    List<Stock> getReportByNumber(Integer repNumber);
}
