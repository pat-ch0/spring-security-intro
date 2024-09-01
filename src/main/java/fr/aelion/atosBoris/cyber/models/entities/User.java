package fr.aelion.atosBoris.cyber.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USERS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Integer vs Long
    @Column(unique = true)
    @Size(min = 10)
    @Email
    // TODO: store encrypted
    private String email;
    @Column(name = "password")
    @NotNull
    @Size(min = 16)
    // password pattern is validated with credentials class (here only Hash is stored)
    private String pwd;

    /*
    * Clean sensitive value for public usage
    * TODO: implement as DTO or other object mapping lib
    * @return current user without sensitive data
     */
    public User toPublicUser() {
        this.setPwd(null);
        return this;
    }
}