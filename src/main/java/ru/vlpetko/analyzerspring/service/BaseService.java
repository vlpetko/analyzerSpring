package ru.vlpetko.analyzerspring.service;

import ru.vlpetko.analyzerspring.model.Stock;

public interface BaseService {

    Stock saveStock(Stock stock);

    void deleteStock(Long stockId);

    Stock redactStock(Stock stock);

    void uploadFile(String path);
}
