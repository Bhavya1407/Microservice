package com.microservice.login.user;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.microservice.login.exception.UserAlreadyExistsException;
import com.microservice.login.user.Registration.RegistrationRequest;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	@Override
	
	public List<User>getUsers(){
		return userRepository.findAll() ;
	}
	
	@Override
	
	  public User resgistrationUser(RegistrationRequest request){
		Optional<User> user= this.findByEmail(request.email());
		if(user.isPresent()) {
			throw new UserAlreadyExistsException(
			"User with email "+request.email()+"already exists");
		}
		var newUser = new User();
		newUser.setFirstName(request.firstName());
		newUser.setLastName(request.lastName());
		newUser.setEmail(request.email());
		newUser.setPassword(passwordEncoder.encode(request.password()));
		newUser.setRole(request.role());
		return userRepository.save(newUser);
	}
	
	@Override
	
	 public Optional<User>findByEmail(String email){
		return userRepository.findByEmail(email);
	}
}
