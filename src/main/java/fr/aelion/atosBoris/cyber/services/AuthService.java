package fr.aelion.atosBoris.cyber.services;

import fr.aelion.atosBoris.cyber.models.entities.User;
import fr.aelion.atosBoris.cyber.repositories.UserRepository;
import fr.aelion.atosBoris.cyber.utils.HashUtils;
import fr.aelion.atosBoris.cyber.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final HashUtils hashUtils;
    private final UserRepository userRepo;

    @Autowired
    public AuthService(HashUtils hashUtils, UserRepository userRepo) {
        this.hashUtils = hashUtils;
        this.userRepo = userRepo;
    }

    public User signup(User u) {
        u.setPwd(this.hashUtils.generate(u.getPwd()));
        // save in db
        return this.userRepo.save(u); // /!\ dto (controller...)
    }

    public String signin(User u)  {
        // TODO: custom exception
        return this.generateToken(
                this.userRepo.findByEmailAndPwd(u.getEmail(), this.hashUtils.generate(u.getPwd()))
                        .orElseThrow(RuntimeException::new)
        );
    }

    private String generateToken(User u) {
        // TODO: create a JWT token
        return TokenUtils.generateJWT(u);
    }
}