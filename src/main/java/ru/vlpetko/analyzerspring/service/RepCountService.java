package ru.vlpetko.analyzerspring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vlpetko.analyzerspring.repository.RepCountRepository;


public interface RepCountService {

    public int repCountAmounter();
}
