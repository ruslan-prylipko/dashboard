package ua.com.dashboard.view.rate;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String iso;
    private String code;
    private String name;
    @EqualsAndHashCode.Exclude
    private String rate;
    @EqualsAndHashCode.Exclude
    private String trend;
    private String date;

    @Override
    public String toString() {
        return "iso = " + iso + "\n" +
                "code = " + code + "\n" +
                "name = " + name + "\n" +
                "rate = " + rate + "\n" +
                "trend = " + trend + "\n" +
                "date = " + date + "\n\n";
    }
}
