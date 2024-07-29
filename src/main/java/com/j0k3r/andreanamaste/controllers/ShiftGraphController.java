package com.j0k3r.andreanamaste.controllers;

import com.j0k3r.andreanamaste.exceptions.ShiftException;
import com.j0k3r.andreanamaste.http.response.ShiftResponse;
import com.j0k3r.andreanamaste.services.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

@Controller
public class ShiftGraphController {

    @Autowired
    private ShiftService shiftService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @QueryMapping
    public ShiftResponse getShiftById(@Argument String id) throws ShiftException {
        return  shiftService.getShiftById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @QueryMapping
    public Page<ShiftResponse> getAllShifts(
            @Argument Integer size,
            @Argument String sort,
            @Argument Integer page,
            @Argument String direction){
        Pageable pageable = PageRequest.of(
                page == null ? 1 : page,
                size == null ? 10 : size,
                Sort.by(Sort.Direction.fromString(direction != null ? direction : "ASC"),
                        sort != null && !sort.isEmpty() ? sort : "date"));
        return shiftService.getAllShifts(pageable);
    }

}
