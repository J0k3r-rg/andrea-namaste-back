package com.j0k3r.andreanamaste.repositories;

import com.j0k3r.andreanamaste.models.Shift;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Range;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, String> {

    Optional<Shift> findByDateAndHour(LocalDate date, LocalTime hour);

    List<Shift> findByDateAndIsBooked(LocalDate date, Boolean isBooked);

    Page<Shift> findByDate(LocalDate date, Pageable pageable);

    Page<Shift> findByDateAndIsBooked(LocalDate date, Boolean isBooked, Pageable pageable);

    Page<Shift> findByIsBooked(Boolean booked, Pageable pageable);
}
