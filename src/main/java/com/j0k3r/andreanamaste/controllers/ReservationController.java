package com.j0k3r.andreanamaste.controllers;

import com.j0k3r.andreanamaste.exceptions.ProductException;
import com.j0k3r.andreanamaste.exceptions.ShiftException;
import com.j0k3r.andreanamaste.exceptions.UserException;
import com.j0k3r.andreanamaste.http.request.ReservationRequest;
import com.j0k3r.andreanamaste.services.ReservationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping()
    public ResponseEntity<?> createReservation(@RequestBody @Valid ReservationRequest reservationRequest) throws ShiftException, UserException, ProductException {
        return ResponseEntity.ok(
                reservationService.createReservation(reservationRequest)
        );
    }

}
