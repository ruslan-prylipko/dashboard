package ua.com.dashboard.view.rate;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Class that represents json file with rates.
 */
@Setter
@Getter
@EqualsAndHashCode
public class Rates {
    private Rate[] data;
}
