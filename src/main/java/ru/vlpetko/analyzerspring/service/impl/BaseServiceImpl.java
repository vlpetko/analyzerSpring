package ru.vlpetko.analyzerspring.service.impl;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vlpetko.analyzerspring.model.Stock;
import ru.vlpetko.analyzerspring.repository.StockRepository;
import ru.vlpetko.analyzerspring.service.BaseService;
import ru.vlpetko.analyzerspring.service.RepCountService;

import javax.persistence.EntityNotFoundException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.List;

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

    @Override
    public void uploadFile(String path){
        if (path.toLowerCase().endsWith(".csv")){
            try{
                List<Stock> stocksFromFile = new CsvToBeanBuilder(new FileReader(path))
                        .withSkipLines(1)
                        .withType(Stock.class)
                        .build()
                        .parse();

                String tickerName = path.substring(path.lastIndexOf("\\") + 1, path.lastIndexOf(".csv"));
                int repCount = repCountService.getRepCount();

                for (Stock stock : stocksFromFile){
                    stock.setStockName(tickerName);
                    stock.setReportNumber(repCount + 1);
                    stock.setUploadDate(LocalDate.now());
                    stockRepository.save(stock);
                }
                repCountService.repCountAmounter();
            } catch (FileNotFoundException e) {
                System.out.println("Файл не найден");
                e.printStackTrace();
            }
        }
    }
}
