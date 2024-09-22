package com.j0k3r.andreanamaste.services;

import com.j0k3r.andreanamaste.exceptions.ProductException;
import com.j0k3r.andreanamaste.exceptions.ReservationException;
import com.j0k3r.andreanamaste.exceptions.ShiftException;
import com.j0k3r.andreanamaste.exceptions.UserException;
import com.j0k3r.andreanamaste.http.request.ReservationRequest;
import com.j0k3r.andreanamaste.http.response.ReservationResponse;
import com.j0k3r.andreanamaste.models.Reservation;
import com.j0k3r.andreanamaste.repositories.ReservationRepository;
import com.j0k3r.andreanamaste.utils.ReservationUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationUtils reservationUtils;


    @Transactional
    public ReservationResponse createReservation(ReservationRequest reservationRequest) throws ShiftException, UserException, ProductException {

        if(reservationRepository.existsByIsPaidOrFinallyShiftAndUserId(false,false,reservationRequest.getUserId())){
            throw new ShiftException("Usted ya tiene un turno reservado",500);
        }

        Reservation reservation = reservationRepository.save(reservationUtils.toReservation(reservationRequest));
        reservation.getShift().setIsBooked(true);
        return ReservationUtils.toReservationResponse(reservation);
    }

    public Page<ReservationResponse> getAllsReservations(Pageable pageable) {
        return reservationRepository.findAll(pageable).map(ReservationUtils::toReservationResponse);
    }

    public ReservationResponse getReservationById(String id) throws ReservationException {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(
                () -> new ReservationException("Turno no encontrado", 400)
        );
        return ReservationUtils.toReservationResponse(reservation);
    }
}
