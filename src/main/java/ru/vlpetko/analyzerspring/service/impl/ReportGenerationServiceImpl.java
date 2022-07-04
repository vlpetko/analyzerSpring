package ru.vlpetko.analyzerspring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vlpetko.analyzerspring.model.Stock;
import ru.vlpetko.analyzerspring.repository.StockRepository;
import ru.vlpetko.analyzerspring.service.ReportGenerationService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReportGenerationServiceImpl implements ReportGenerationService {

    private final StockRepository stockRepository;

    @Override
    public List<Stock> getAllStocks(){
        return stockRepository.findAllDistinctByReportNumber();
    }
    @Override
    public List<Stock> getStocksByReportNumber(int repNumber){
        return stockRepository.getAllByReportNumber(repNumber);
    }
}
