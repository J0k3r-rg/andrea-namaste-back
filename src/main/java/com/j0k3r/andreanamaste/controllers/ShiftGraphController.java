package com.j0k3r.andreanamaste.controllers;

import com.j0k3r.andreanamaste.exceptions.ShiftException;
import com.j0k3r.andreanamaste.http.response.ErrorResponse;
import com.j0k3r.andreanamaste.http.response.ResponseClient;
import com.j0k3r.andreanamaste.http.response.ShiftResponse;
import com.j0k3r.andreanamaste.services.ShiftService;
import com.j0k3r.andreanamaste.utils.PageableUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
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
            @Argument List<String> sorts,
            @Argument Integer page,
            @Argument String direction){

        Pageable pageable = PageableUtils.createPageable(
                page,size,direction,sorts,"date"
        );
        return shiftService.getAllShifts(pageable);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @QueryMapping
    public ResponseClient<?> getShiftByDateFree(
            @Argument Integer day,
            @Argument Integer month,
            @Argument Integer year
    ){
        try{
            LocalDate date = LocalDate.of(year,month,day);
            ResponseClient<ShiftResponse> response = new ResponseClient<>();
            response.setContent(shiftService.getShiftsByDateFree(date));
            response.setError(null);
            return response;
        } catch (DateTimeException ex){
            return ResponseClient.builder()
                    .content(null)
                    .error(ErrorResponse.builder()
                            .status(500)
                            .message(ex.getMessage())
                            .build())
                    .build();
        }
    }

}
