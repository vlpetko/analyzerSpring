package ru.vlpetko.analyzerspring.controller.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReportsMenuDto {

    private int reportNumber;

    private String stockName;

    private LocalDate MinUploadDate;

    private LocalDate MaxUploadDate;


}
