package ua.com.dashboard.services.rate;

import ua.com.dashboard.view.rate.Rate;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

public interface RateService {
	List<Rate> getAllRates(final String date) throws JsonIOException, JsonSyntaxException, IOException, ParseException;
	Iterable<Rate> saveAllRates(final List<Rate> rates);
}
