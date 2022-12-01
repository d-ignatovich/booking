package rest.persistence.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "t_role")
public class Role implements GrantedAuthority {
    @Id
    private Long id;
    private String name;
    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role(){}
    public Role(Long l, String role_user) {
        id = l;
        name = role_user;
    }

    @Override
    public String getAuthority() {
        return getName();
    }
}
