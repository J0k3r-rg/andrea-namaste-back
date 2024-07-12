package com.j0k3r.andreanamaste.controllers;

import com.j0k3r.andreanamaste.controllers.filters.shiftfilters.ShiftValidation;
import com.j0k3r.andreanamaste.controllers.filters.shiftfilters.ShiftValidationRange;
import com.j0k3r.andreanamaste.exceptions.ShiftException;
import com.j0k3r.andreanamaste.http.request.DateRequest;
import com.j0k3r.andreanamaste.http.request.ShiftCreateRange;
import com.j0k3r.andreanamaste.http.request.ShiftRequest;
import com.j0k3r.andreanamaste.services.ShiftService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/shift")
public class ShiftController {

    @Autowired
    private ShiftService shiftService;

    @Autowired
    private List<ShiftValidation> shiftValidations;

    @Autowired
    private List<ShiftValidationRange> shiftValidationRanges;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/admin/range")
    public ResponseEntity<?> createShiftRange(@RequestBody @Valid ShiftCreateRange shiftCreateRange) throws ShiftException {
        for (ShiftValidationRange shiftValidationRange : shiftValidationRanges) {
            shiftValidationRange.validate(shiftCreateRange);
        }
        return ResponseEntity.ok(shiftService.createShiftRange(shiftCreateRange));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/admin")
    public ResponseEntity<?> createShift(@RequestBody @Valid ShiftRequest shiftCreateRange) throws ShiftException {
        for (ShiftValidation shiftValidation : shiftValidations) {
            shiftValidation.validate(shiftCreateRange);
        }
        return ResponseEntity.ok(shiftService.createShit(shiftCreateRange));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin")
    public ResponseEntity<?> getShifts(@PageableDefault(sort = {"date","hour"}) Pageable pageable) {
        return ResponseEntity.ok(shiftService.getAllShifts(pageable));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin/date")
    public ResponseEntity<?> getShiftsByDateAdmin(
            @PageableDefault(sort = {"date","hour"}) Pageable pageable,
            @RequestParam(required = false) LocalDate date,
            @RequestParam(required = false) Boolean booked) {
        if(booked != null && date != null) {
            return ResponseEntity.ok(shiftService.getAllShifts(date, booked, pageable));
        }
        if(booked != null) {
            return ResponseEntity.ok(shiftService.getAllShifts(booked, pageable));
        }
        return ResponseEntity.ok(shiftService.getAllShifts(date,pageable));
    }


    @GetMapping
    public ResponseEntity<?> getShiftsByUser(@RequestBody DateRequest date) {
        return ResponseEntity.ok(shiftService.getShiftsByDateFree(date.getDate()));
    }

}
