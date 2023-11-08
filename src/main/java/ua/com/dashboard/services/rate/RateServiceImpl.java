package ua.com.dashboard.services.rate;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ua.com.dashboard.view.rate.Rate;
import ua.com.dashboard.view.rate.Rates;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.List;

@Service
public class RateServiceImpl implements RateService {

	@Value("${api.minfin.com.ua.rates}")
	private String strURL;

	@Override
	public List<Rate> getRates(final String date) throws JsonIOException, JsonSyntaxException, IOException {
		URL url = new URL(strURL + date);
		Gson gson = new Gson();
		Rates rates = gson.fromJson(new JsonReader(new InputStreamReader(url.openConnection().getInputStream())),
				Rates.class);
		return List.of(rates.getData());
	}
}
