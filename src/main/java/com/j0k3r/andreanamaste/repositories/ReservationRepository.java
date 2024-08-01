package com.j0k3r.andreanamaste.repositories;

import com.j0k3r.andreanamaste.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, String> {

    List<Reservation> findByUserId(String id);

    Boolean existsByIsPaidOrFinallyShiftAndUserId(Boolean isPaid, Boolean finallyShift, String userId);
}
