package ru.vlpetko.analyzerspring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "stocks")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
