package se.lexicon.todoapi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "roles")
@EqualsAndHashCode(exclude = "roles")

@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(updatable = false)
    private String email;
    @Column(nullable = false, length = 100)
    private String password;
    private boolean expired;

    @ManyToMany
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public void addRole(Role role) throws IllegalAccessException{
        if (role == null) throw new IllegalAccessException("Role is null");
        if (roles == null) roles = new HashSet<>();
        roles.add(role);
    }

    public void removeRole(Role role) throws IllegalAccessException{
        if (role == null) throw new IllegalAccessException("Role is null");
        if (roles != null) {
            roles.remove(role);
        } else {
            // TODO: 31/10/2023 throw exception if needed...
        }
    }


}
