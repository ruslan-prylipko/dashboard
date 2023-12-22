package ua.com.dashboard.view.user;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NonNull private String firstName;
    @NonNull private String lastName;
    @NonNull private String username;
    @EqualsAndHashCode.Exclude
    @NonNull private String password;
    @EqualsAndHashCode.Exclude
    private String role = "ROLE_USER";

    @Override
    public String toString() {
        return "User " +
                "id = " + id +
                " firstName = " + firstName +
                " lastName = " + lastName +
                " username = " + username + "\n";
    }
}
