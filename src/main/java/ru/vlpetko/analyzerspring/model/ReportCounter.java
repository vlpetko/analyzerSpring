package ru.vlpetko.analyzerspring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "repCounter")
public class ReportCounter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long repId;

    private int repCounterAmount;
}
