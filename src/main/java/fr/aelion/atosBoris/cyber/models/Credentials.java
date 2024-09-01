package fr.aelion.atosBoris.cyber.models;

import fr.aelion.atosBoris.cyber.models.entities.User;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Credentials {
    @Column(unique = true)
    @Size(min = 10)
    @Email
    private String email;
    @Column(name = "password")
    @Size(min = 8)
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "MID Password") // Minimum 8 characters, at least 1 letter and 1 number
    private String pwd;

    public User toUser() {
        return new User(null, this.email, this.pwd);
    }
}
