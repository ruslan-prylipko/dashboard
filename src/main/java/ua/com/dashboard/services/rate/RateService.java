package ua.com.dashboard.services.rate;

import ua.com.dashboard.view.rate.Rate;

import java.io.IOException;
import java.util.List;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

public interface RateService {
	List<Rate> getRates(final String date) throws JsonIOException, JsonSyntaxException, IOException;
}