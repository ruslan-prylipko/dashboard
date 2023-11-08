package ua.com.dashboard.view.rate;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class Rate {
    @EqualsAndHashCode.Include
    private String iso;
    @EqualsAndHashCode.Include
    private String code;
    private String name;
    private String rate;
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
