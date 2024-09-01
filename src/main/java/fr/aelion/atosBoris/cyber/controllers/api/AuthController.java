package fr.aelion.atosBoris.cyber.controllers.api;

import fr.aelion.atosBoris.cyber.models.Credentials;
import fr.aelion.atosBoris.cyber.models.entities.User;
import fr.aelion.atosBoris.cyber.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    // protected or default (package) => to allow better testing
    // @Autowired
    // AuthService service;
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public User signup(@RequestBody @Valid Credentials c) {
        // hash + salt + db storage
        return this.authService.signup(c.toUser()).toPublicUser();
    }

    @PostMapping("/signin")
    public String signin(@RequestBody User u) {
        // hash + salt + db select
        return this.authService.signin(u);
    }
}