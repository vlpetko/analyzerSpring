package ru.vlpetko.analyzerspring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vlpetko.analyzerspring.model.Stock;
import ru.vlpetko.analyzerspring.repository.StockRepository;
import ru.vlpetko.analyzerspring.service.BaseService;
import ru.vlpetko.analyzerspring.service.RepCountService;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class BaseServiceImpl implements BaseService {

    private final StockRepository stockRepository;

    private final RepCountService repCountService;

    @Override
    public Stock saveStock(Stock stock) {
        stock.setUploadDate(LocalDate.now());
        stock.setReportNumber(repCountService.repCountAmounter());
        return stockRepository.save(stock);
    }

    @Override
    public void deleteStock(Long stockId) {
        try{
            stockRepository.deleteById(stockId);
        } catch(EntityNotFoundException e) {
            throw new IllegalArgumentException("Удаляемая акция не найдена: " + stockId);
        }
    }
// ne realizovano
    @Override
    public Stock redactStock(Stock stock) {
        return stock;
    }
}
