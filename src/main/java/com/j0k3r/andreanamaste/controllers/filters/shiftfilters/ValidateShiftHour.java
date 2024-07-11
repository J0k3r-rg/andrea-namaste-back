package com.j0k3r.andreanamaste.controllers.filters.shiftfilters;

import com.j0k3r.andreanamaste.exceptions.ShiftException;
import com.j0k3r.andreanamaste.http.request.ShiftRequest;
import org.springframework.stereotype.Component;

@Component
public class ValidateShiftHour implements ShiftValidation{

    @Override
    public void validate(ShiftRequest shift) throws ShiftException {
        if(shift.getHour().getMinute() > 0)
            throw new ShiftException("La hora del turno debe ser en punto",400);
    }
}
