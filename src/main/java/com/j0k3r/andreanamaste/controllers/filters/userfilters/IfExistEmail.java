package com.j0k3r.andreanamaste.controllers.filters.userfilters;

import com.j0k3r.andreanamaste.exceptions.UserException;
import com.j0k3r.andreanamaste.http.request.UserRequest;
import com.j0k3r.andreanamaste.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IfExistEmail implements UserValidation{

    @Autowired
    private UserRepository userRepository;

    @Override
    public void validate(UserRequest user) throws UserException {
        if(userRepository.existsByEmail(user.getEmail())){
            throw new UserException("El email ya se encuentra registrado",400);
        }
    }
}
