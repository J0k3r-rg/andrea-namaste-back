package com.j0k3r.andreanamaste.utils;

import com.j0k3r.andreanamaste.exceptions.ProductException;
import com.j0k3r.andreanamaste.exceptions.ShiftException;
import com.j0k3r.andreanamaste.exceptions.UserException;
import com.j0k3r.andreanamaste.http.request.ReservationRequest;
import com.j0k3r.andreanamaste.http.response.ReservationResponse;
import com.j0k3r.andreanamaste.models.Product;
import com.j0k3r.andreanamaste.models.Reservation;
import com.j0k3r.andreanamaste.models.Shift;
import com.j0k3r.andreanamaste.repositories.ProductRepository;
import com.j0k3r.andreanamaste.repositories.ShiftRepository;
import com.j0k3r.andreanamaste.security.models.User;
import com.j0k3r.andreanamaste.security.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReservationUtils {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShiftRepository shiftRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public Reservation toReservation(ReservationRequest reservationRequest) throws UserException, ShiftException, ProductException {
        User user = userRepository.findById(reservationRequest.getUserId()).orElseThrow(
                () -> new UserException("Usuario no encontrado", 400)
        );
        Shift shift = shiftRepository.findById(reservationRequest.getShiftId()).orElseThrow(
                () -> new ShiftException("Turno no encontrado", 400)
        );
        Product product = productRepository.findById(reservationRequest.getProductId()).orElseThrow(
                () -> new ProductException("Producto no encontrado", 400)
        );
        shift.setIsBooked(true);
        return Reservation.builder()
                .user(user)
                .shift(shift)
                .product(product)
                .build();
    }

    public static ReservationResponse toReservationResponse(Reservation reservation) {
        return ReservationResponse.builder()
                .id(reservation.getId())
                .isPaid(reservation.getIsPaid())
                .urlMeet(reservation.getUrlMeet())
                .user(UserUtils.toUserResponse(reservation.getUser()))
                .product(ProductUtils.toProductResponse(reservation.getProduct()))
                .shift(ShiftUtils.toShiftResponse(reservation.getShift()))
                .build();
    }
}
