package ru.vlpetko.analyzerspring.service;

import org.springframework.core.io.ByteArrayResource;
import ru.vlpetko.analyzerspring.model.Stock;

import java.io.IOException;
import java.util.List;

public interface GenerateFileService {

    ByteArrayResource generateStocksInfoDocument(List<List<String>> stockLists, String fileName) throws IOException;
}
