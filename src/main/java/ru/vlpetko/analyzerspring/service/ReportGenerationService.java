package ru.vlpetko.analyzerspring.service;

import ru.vlpetko.analyzerspring.model.Stock;

import java.util.List;

public interface ReportGenerationService {

    List<Stock> getAllStocks();
}
