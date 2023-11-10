package ua.com.dashboard.services.rate;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ua.com.dashboard.repositories.rate.RateRepository;
import ua.com.dashboard.utils.DateUtil;
import ua.com.dashboard.view.rate.Rate;
import ua.com.dashboard.view.rate.Rates;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.text.ParseException;
import java.util.*;

@Service
@Log4j2
public class RateServiceImpl implements RateService {

	private final RateRepository rateRepository;

	@Autowired
	public RateServiceImpl(RateRepository rateRepository) {
		this.rateRepository = rateRepository;
	}

	@Value("${api.minfin.com.ua.rates}")
	private String strURL;

	@Override
	public List<Rate> getRates(final String date)
			throws JsonIOException, JsonSyntaxException, IOException, ParseException {
		List<Rate> findRates = rateRepository.findAllByDate(
				DateUtil.convert("dd-MM-yyyy", "dd.MM.yyyy", date));

		if (!findRates.isEmpty()) {
			log.info("Reads rates from database");
			return findRates;
		}

		URL url = new URL(strURL + date);
		Gson gson = new Gson();
		Rates rates = gson.fromJson(new JsonReader(new InputStreamReader(url.openConnection().getInputStream())),
				Rates.class);
		List<Rate> r = Arrays.asList(rates.getData());
		rateRepository.saveAll(r);
		log.info("Reads rates from website");
		return r;
	}
}
