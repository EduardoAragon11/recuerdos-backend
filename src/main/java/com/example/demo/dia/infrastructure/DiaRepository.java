package com.example.demo.dia.infrastructure;

import com.example.demo.dia.domain.Dia;
import com.example.demo.dia.dto.SimpleDiaResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DiaRepository extends JpaRepository<Dia, Long> {
    @Query("select d from Dia d where extract(year from d.date) = :year_ and extract(month from d.date) = :month_")
    List<Dia> findAllByYearAndMonth(String year_, String month_);

    Optional<Dia> findDiaByDate(LocalDate date);
}
