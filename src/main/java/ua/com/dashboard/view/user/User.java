package ua.com.dashboard.view.user;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;
import ua.com.dashboard.view.user.role.Role;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @NonNull private String firstName;
    @NonNull private String lastName;
    @NonNull private String username;
    
    @EqualsAndHashCode.Exclude
    @NonNull private String password;
    
    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private List<Role> roles;
    
    @Override
    public String toString() {
        return "User " +
                "id = " + id +
                " firstName = " + firstName +
                " lastName = " + lastName +
                " username = " + username + "\n";
    }
}
