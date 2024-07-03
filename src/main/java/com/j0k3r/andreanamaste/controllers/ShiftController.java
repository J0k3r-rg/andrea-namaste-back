package com.j0k3r.andreanamaste.controllers;

import com.j0k3r.andreanamaste.services.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/shift")
public class ShiftController {

    @Autowired
    private ShiftService shiftService;



}
