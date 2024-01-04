package com.microservice.login.user;

import java.util.List;
import java.util.Optional;

import com.microservice.login.user.Registration.RegistrationRequest;

public interface IUserService {
List<User>getUsers();
User resgistrationUser(RegistrationRequest request);
Optional<User>findByEmail(String email);

}
