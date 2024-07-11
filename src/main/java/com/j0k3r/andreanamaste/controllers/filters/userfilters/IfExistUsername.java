package com.j0k3r.andreanamaste.controllers.filters.userfilters;

import com.j0k3r.andreanamaste.exceptions.UserException;
import com.j0k3r.andreanamaste.http.request.UserRequest;
import com.j0k3r.andreanamaste.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IfExistUsername implements UserValidation{

    @Autowired
    private UserRepository userRepository;

    @Override
    public void validate(UserRequest user) throws UserException {
        if(userRepository.existsByUsername(user.getUsername())){
            throw new UserException("El nombre de usuario ya se encuentra registrado",400);
        }
    }
}
