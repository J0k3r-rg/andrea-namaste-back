package com.j0k3r.andreanamaste.controllers.filters.userfilters;

import com.j0k3r.andreanamaste.exceptions.UserException;
import com.j0k3r.andreanamaste.http.request.UserRequest;

public interface UserValidation {

    void validate(UserRequest user) throws UserException;

}
