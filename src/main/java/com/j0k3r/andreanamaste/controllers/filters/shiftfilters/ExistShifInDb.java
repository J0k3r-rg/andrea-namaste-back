package com.j0k3r.andreanamaste.controllers.filters.shiftfilters;

import com.j0k3r.andreanamaste.exceptions.ShiftException;
import com.j0k3r.andreanamaste.http.request.ShiftRequest;
import com.j0k3r.andreanamaste.repositories.ShiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExistShifInDb implements ShiftValidation{

    @Autowired
    private ShiftRepository shiftRepository;

    @Override
    public void validate(ShiftRequest shift) throws ShiftException {
        if(shiftRepository.findByDateAndHour(shift.getDate(), shift.getHour()).isPresent())
            throw new ShiftException("El turno ya se encuentra creado",400);
    }
}
