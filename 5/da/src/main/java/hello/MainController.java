package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller    // This means that this class is a Controller
@RequestMapping(path="/") // This means URL's start with /demo (after Application path)
public class MainController {
	@Autowired // This means to get the bean called userRepository
	private UserRepository userRepository;



	@GetMapping(path="/addsd")
	public 	@ResponseBody
    String t(@RequestParam String name) {
		return "aaaa"+name;
	}

	@PostMapping(path="/add") // Map ONLY GET Requests
	public @ResponseBody
    String addNewUser (@RequestParam String name, @RequestParam String phone, @RequestParam String password) {
		User n = new User();
		n.setName(name);
		n.setPhone(phone);
		n.setPassword(password);
		userRepository.save(n);
		return "Saved";
	}


	@GetMapping(path="/all")
	public @ResponseBody
    Iterable<User> getAllUsers() {
		// This returns a JSON or XML with the users
		return userRepository.findAll();
	}
}
