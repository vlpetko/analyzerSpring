package ru.vlpetko.analyzerspring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vlpetko.analyzerspring.model.ReportCounter;
import ru.vlpetko.analyzerspring.repository.RepCountRepository;
import ru.vlpetko.analyzerspring.service.RepCountService;

@Service
@RequiredArgsConstructor
public class RepCountServiceImpl implements RepCountService {

    private final RepCountRepository repCountRepository;

    @Override
    public int repCountAmounter(){

        ReportCounter reportCounter = repCountRepository.getById((long) 1);
        reportCounter.setRepCounterAmount(reportCounter.getRepCounterAmount() + 1);
        repCountRepository.save(reportCounter);

        return reportCounter.getRepCounterAmount();
    }
}
