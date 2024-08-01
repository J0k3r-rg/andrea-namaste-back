package com.j0k3r.andreanamaste.repositories;

import com.j0k3r.andreanamaste.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, String> {

    List<Reservation> findByUserId(String id);

    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN TRUE ELSE FALSE END FROM " +
            "Reservation r WHERE (r.isPaid = :isPaid OR r.finallyShift = :finallyShift) " +
            "AND r.user.id = :userId")
    Boolean existsByIsPaidOrFinallyShiftAndUserId(
            @Param("isPaid") Boolean isPaid,
            @Param("finallyShift") Boolean finallyShift,
            @Param("userId") String userId);
}
