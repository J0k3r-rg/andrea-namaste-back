package com.j0k3r.andreanamaste.services;

import com.j0k3r.andreanamaste.http.response.ShiftResponse;
import com.j0k3r.andreanamaste.repositories.ShiftRepository;
import com.j0k3r.andreanamaste.utils.ShiftUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShiftService {

    @Autowired
    private ShiftRepository shiftRepository;

    public List<ShiftResponse> getShifts(){
        return shiftRepository.findAll().stream().map(ShiftUtils::toShiftResponse).toList();
    }

}
