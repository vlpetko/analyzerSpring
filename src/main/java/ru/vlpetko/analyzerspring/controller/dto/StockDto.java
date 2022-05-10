package ru.vlpetko.analyzerspring.controller.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class StockDto {

    private Long id;

    private LocalDate tradingDate;

    private double openPrice;

    private double highPrice;

    private double lowPrice;

    private double closePrice;

    private double adjClosePrice;

    private int volume;

    private String stockName;

    private int reportNumber;

    private LocalDate uploadDate;
}
