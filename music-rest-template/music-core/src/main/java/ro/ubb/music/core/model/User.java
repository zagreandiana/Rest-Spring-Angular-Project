package ro.ubb.music.core.model;

import lombok.*;
import ro.ubb.catalog.core.utils.UserRoles;
import ro.ubb.catalog.core.utils.UserStatuses;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "users")
public class User extends BaseEntity<Long> {

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRoles role;
    @Enumerated(EnumType.STRING)
    private UserStatuses status;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", status=" + status +
                '}';
    }
}
