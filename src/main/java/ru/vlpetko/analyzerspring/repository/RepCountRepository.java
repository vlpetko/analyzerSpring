package ru.vlpetko.analyzerspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vlpetko.analyzerspring.model.ReportCounter;

@Repository
public interface RepCountRepository extends JpaRepository<ReportCounter, Long> {
}
