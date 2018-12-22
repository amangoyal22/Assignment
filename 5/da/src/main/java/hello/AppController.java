package hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {
    @GetMapping(path="/Welcome")
    public String welcome(){
        return "welcome";
    }
    @GetMapping(path="/login")
    public String otp(){
        return "login";
    }
}
