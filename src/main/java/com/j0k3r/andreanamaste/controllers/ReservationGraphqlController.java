package com.j0k3r.andreanamaste.controllers;

import com.j0k3r.andreanamaste.exceptions.ReservationException;
import com.j0k3r.andreanamaste.http.response.ReservationResponse;
import com.j0k3r.andreanamaste.services.ReservationService;
import com.j0k3r.andreanamaste.utils.PageableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ReservationGraphqlController {

    @Autowired
    private ReservationService reservationService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @QueryMapping
    public Page<ReservationResponse> getReservations(
            @Argument Integer size,
            @Argument List<String> sorts,
            @Argument Integer page,
            @Argument String direction) {
        Pageable pageable = PageableUtils.createPageable(
                page,size,direction,sorts,"shiftDate"
        );
        return reservationService.getAllsReservations(pageable);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @QueryMapping
    public ReservationResponse getReservationById(@Argument String id) throws ReservationException {
        return reservationService.getReservationById(id);
    }

}
