package com.j0k3r.andreanamaste.services;

import com.j0k3r.andreanamaste.exceptions.ProductException;
import com.j0k3r.andreanamaste.exceptions.ShiftException;
import com.j0k3r.andreanamaste.exceptions.UserException;
import com.j0k3r.andreanamaste.http.request.ReservationRequest;
import com.j0k3r.andreanamaste.http.response.ReservationResponse;
import com.j0k3r.andreanamaste.models.Reservation;
import com.j0k3r.andreanamaste.repositories.ReservationRepository;
import com.j0k3r.andreanamaste.utils.ReservationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationUtils reservationUtils;

    public ReservationResponse createReservation(ReservationRequest reservationRequest) throws ShiftException, UserException, ProductException {
        Reservation reservation = reservationUtils.toReservation(reservationRequest);
        reservationRepository.save(reservation);
        return reservationUtils.toReservationResponse(reservation);
    }

}
