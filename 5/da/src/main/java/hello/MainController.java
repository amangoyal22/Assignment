package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Controller    // This means that this class is a Controller
@RequestMapping(path="/") // This means URL's start with /demo (after Application path)
public class MainController {
	@Autowired // This means to get the bean called userRepository
	private UserRepository userRepository;
	@Autowired // This means to get the bean called OTP Repository
	private OtpRepositry ORepository;



	@GetMapping(path="/addsd")
	public 	@ResponseBody
    String t(@RequestParam String name) {
		return "aaaa"+name;
	}

	@PostMapping(path="/add") // Map ONLY POST Requests
	public String addNewUser (@RequestParam String name, @RequestParam String phone, @RequestParam String password) {
		User n = new User();
		n.setName(name);
		n.setPhone(phone);
		n.setPassword(password);
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		n.setT(Time.valueOf(formatter.format(date)));

		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
	//	Date date = new Date();
		n.setD(java.sql.Date.valueOf(formatter1.format(date)));

		userRepository.save(n);
		return "welcome";
	}

	@PostMapping(path="/gOTP") // Map ONLY POST Requests
	public String addNewUser (@RequestParam String phone) {
		uotp n = new uotp();
		n.phone = phone;
		n.otp = n.otpgene();
		ORepository.save(n);
		return "OTPLogin";
	}

	@PostMapping(path="/loginotp") // Map ONLY POST Requests
	public 	String loginOtp (@RequestParam String phone, @RequestParam String otp, Model model) {
		uotp n = new uotp();
		n.phone = phone;
		n.otp = otp;

		for (uotp x:ORepository.findAll()) {
			if(x.phone.equals(phone) && x.otp.equals(otp)){
				model.addAttribute("userp",phone);
				return "successLogin";
			}
		};
		return "Fail";
	}
	@GetMapping(path="/all")
	public @ResponseBody
    Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}
}
