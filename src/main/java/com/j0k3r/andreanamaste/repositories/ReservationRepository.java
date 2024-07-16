package com.j0k3r.andreanamaste.repositories;

import com.j0k3r.andreanamaste.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, String> {
}
