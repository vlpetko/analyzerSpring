package ru.vlpetko.analyzerspring.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.vlpetko.analyzerspring.controller.dto.ReportsMenuDto;
import ru.vlpetko.analyzerspring.controller.dto.StockDto;
import ru.vlpetko.analyzerspring.model.Stock;

import java.util.List;

@Mapper
public interface StockMapper {

    StockMapper INSTANCE = Mappers.getMapper(StockMapper.class);

    Stock mapToStock(StockDto stockDto);

    StockDto mapToStockDto(Stock stock);

    List<ReportsMenuDto> mapToReportsMenuDtoList(List<Stock> stock);
}
