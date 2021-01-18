package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.service.UserDetailsServiceImpl;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {

	private final UserDetailsServiceImpl userDetailsService;
	@Autowired
	public UserController(UserDetailsServiceImpl userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@GetMapping()
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String printWelcome(ModelMap model, Principal principal) {
		List<String> messages = new ArrayList<>();
		messages.add("Hello, " + principal.getName() + "!");
		messages.add("I'm Spring MVC-SECURITY application");
		messages.add("5.2.0 version by sep'19 ");
		model.addAttribute("messages", messages);
		return "user";
	}

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage(ModelMap model) {
		model.addAttribute("title", "Форма входа");
		return "login";
    }

    @GetMapping("/admin")
	public String admin(ModelMap model, Principal principal) {
		List<String> messages = new ArrayList<>();
		messages.add("This is page admin.");
		messages.add("Welcom " + principal.getName());
		model.addAttribute("messages", messages);
//		model.addAttribute("users", userDetailsService.getAllUsers());
		return "admin";
	}

}