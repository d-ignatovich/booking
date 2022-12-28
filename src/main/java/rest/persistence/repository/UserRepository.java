package rest.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rest.persistence.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}