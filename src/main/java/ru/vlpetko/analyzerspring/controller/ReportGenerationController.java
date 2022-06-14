package ru.vlpetko.analyzerspring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.vlpetko.analyzerspring.controller.dto.ReportDto;
import ru.vlpetko.analyzerspring.controller.dto.ReportsMenuDto;
import ru.vlpetko.analyzerspring.mapper.StockMapper;
import ru.vlpetko.analyzerspring.service.ReportGenerationService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping({"/api/report"})
public class ReportGenerationController {

    private final ReportGenerationService reportGenerationService;

    @GetMapping("/getreportnumbers")
    public List<ReportsMenuDto> generateReportList(){
        return StockMapper.INSTANCE.mapToReportsMenuDtoList(reportGenerationService.getAllStocks());
    }

    @PostMapping("/getreport/{reportId}")
    public ReportDto generateReport(@PathVariable Long reportId){
        return null;
    }
}
