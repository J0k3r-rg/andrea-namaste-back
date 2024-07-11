package com.j0k3r.andreanamaste.controllers.filters.shiftfilters;

import com.j0k3r.andreanamaste.exceptions.ShiftException;
import com.j0k3r.andreanamaste.http.request.ShiftRequest;

public interface ShiftValidation {

    public void validate(ShiftRequest shift) throws ShiftException;

}
