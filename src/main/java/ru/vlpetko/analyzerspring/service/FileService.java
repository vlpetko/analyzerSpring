package ru.vlpetko.analyzerspring.service;

import ru.vlpetko.analyzerspring.model.Stock;

public interface FileService {

    void addCsvFile();

    Stock addManualStock(Stock stock);
}
