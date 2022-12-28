package net.javaguides.springboot.Controller;

import net.javaguides.springboot.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.javaguides.springboot.DTO.UserDTO;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

	private UserService userService;

	public UserRegistrationController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@ModelAttribute("users")
    public UserDTO userRegistrationDto() {
        return new UserDTO();
    }
	
	@GetMapping
	public String showRegistrationForm() {
		return "registration2";
	}
	
	@PostMapping
	public String registerUserAccount(@ModelAttribute("users") UserDTO registrationDto) {
		userService.save(registrationDto);
		return "redirect:/registration?success";
	}
}
