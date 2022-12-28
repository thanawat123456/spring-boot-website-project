package net.javaguides.springboot.Service;

import net.javaguides.springboot.DTO.UserDTO;
import net.javaguides.springboot.Model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService{
	User save(UserDTO registrationDto);


}
