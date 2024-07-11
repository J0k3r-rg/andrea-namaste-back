package com.j0k3r.andreanamaste.controllers.filters.shiftfilters;

import com.j0k3r.andreanamaste.exceptions.ShiftException;
import com.j0k3r.andreanamaste.http.request.ShiftCreateRange;
import org.springframework.stereotype.Component;

@Component
public class ValidateRangeStartEnd implements ShiftValidationRange{

    @Override
    public void validate(ShiftCreateRange shiftCreateRange) throws ShiftException {
        if(shiftCreateRange.getStart() == 0 || shiftCreateRange.getEnd() == 0)
            throw new ShiftException("El inicio y el fin no pueden ser 0 o null", 400);
        if(shiftCreateRange.getStart() <= 0 || shiftCreateRange.getStart() > 22)
            throw new ShiftException("El inicio esta fuera de rango", 400);
        if(shiftCreateRange.getStart() > shiftCreateRange.getEnd())
            throw new ShiftException("El final no puede ser menor al inicio", 400);
        if(shiftCreateRange.getStart() == shiftCreateRange.getEnd())
            throw new ShiftException("El inicio y el final no pueden ser iguales", 400);
    }
}
