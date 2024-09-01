package fr.aelion.atosBoris.cyber.repositories;

import fr.aelion.atosBoris.cyber.models.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    // JPA Query Methods
    public Optional<User> findByEmailAndPwd(String email, String pwd);

    // JPQL (JPA Query Language)
    @Query("select u from User u where u.email = ?1")
    User findByEmailAddressWithJPAQuery(String emailAddress);

    // Native SQL
    @Query(nativeQuery = true, value = "select * from users u where u.email = ?1")
    User findByEmailAddressWithJPANativeQuery(String emailAddress);
}
