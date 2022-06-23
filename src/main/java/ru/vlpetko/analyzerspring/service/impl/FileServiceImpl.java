package ru.vlpetko.analyzerspring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vlpetko.analyzerspring.model.Stock;
import ru.vlpetko.analyzerspring.repository.StockRepository;
import ru.vlpetko.analyzerspring.service.FileService;
import ru.vlpetko.analyzerspring.service.RepCountService;

@RequiredArgsConstructor
@Service
public class FileServiceImpl implements FileService {

    private final StockRepository stockRepository;

    private final RepCountService repCountService;

    @Override
    public void addCsvFile() {

    }

    @Override
    public Stock addManualStock(Stock stock) {
        return null;
    }
}
