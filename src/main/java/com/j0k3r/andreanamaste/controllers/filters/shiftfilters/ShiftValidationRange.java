package com.j0k3r.andreanamaste.controllers.filters.shiftfilters;

import com.j0k3r.andreanamaste.exceptions.ShiftException;
import com.j0k3r.andreanamaste.http.request.ShiftCreateRange;

public interface ShiftValidationRange {

    public void validate(ShiftCreateRange shiftCreateRange) throws ShiftException;

}
