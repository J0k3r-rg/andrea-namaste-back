package com.j0k3r.andreanamaste.services;

import com.j0k3r.andreanamaste.repositories.ShiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShiftService {

    @Autowired
    private ShiftRepository shiftRepository;



}
