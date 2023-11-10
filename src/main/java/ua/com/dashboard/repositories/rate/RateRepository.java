package ua.com.dashboard.repositories.rate;

import org.springframework.data.repository.CrudRepository;
import ua.com.dashboard.view.rate.Rate;

import java.util.List;

public interface RateRepository extends CrudRepository<Rate, Long> {
    List<Rate> findAllByDate(String date);
}
