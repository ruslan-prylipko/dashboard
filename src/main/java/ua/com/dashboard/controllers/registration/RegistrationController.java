package ua.com.dashboard.controllers.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ua.com.dashboard.services.user.UserService;
import ua.com.dashboard.utils.Util;
import ua.com.dashboard.view.user.User;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/regpage")
    public String getRegPage() {
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("username") String username,
            @RequestParam("password") String password) {
        Util.stringValidate(firstName, lastName, username, password);
        User user = new User(firstName, lastName, username, new BCryptPasswordEncoder().encode(password));
        user.setRole("role_user");
        userService.save(user);
        return "main";
    }
}
