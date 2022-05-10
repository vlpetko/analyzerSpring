package ru.vlpetko.analyzerspring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.vlpetko.analyzerspring.controller.dto.StockDto;
import ru.vlpetko.analyzerspring.mapper.StockMapper;
import ru.vlpetko.analyzerspring.model.Stock;
import ru.vlpetko.analyzerspring.service.BaseService;

@RestController
@RequiredArgsConstructor
@RequestMapping({"/api/stock"})
public class BaseController {

    private final BaseService baseService;

    @PostMapping("/stocksave")
    public StockDto saveStock(@RequestBody StockDto stockDto){
        Stock stock = baseService.saveStock(StockMapper.INSTANCE.mapToStock(stockDto));
        return StockMapper.INSTANCE.mapToStockDto(stock);
    }

    @DeleteMapping("/stockdelete/{stockId}")
    public void deleteStock(@PathVariable Long stockId,
                            @RequestBody StockDto stockDto){
        baseService.deleteStock(stockId);
    }
    @PostMapping("/stockredact")
    public StockDto redactStock(StockDto stockDto){
        Stock stock = baseService.redactStock(StockMapper.INSTANCE.mapToStock(stockDto));
        return StockMapper.INSTANCE.mapToStockDto(stock);
    }
}
