package rest.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rest.persistence.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
