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
import ua.com.dashboard.services.resource.WebResourceServiceImpl;
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
	private final WebResourceServiceImpl webResourceService;

	@Autowired
	public RateServiceImpl(RateRepository rateRepository, WebResourceServiceImpl webResourceService) {
		this.rateRepository = rateRepository;
		this.webResourceService = webResourceService;
	}

	@Override
	public List<Rate> getRates(final String date)
			throws JsonIOException, JsonSyntaxException, IOException, ParseException {

		List<Rate> ratesList = rateRepository.findAllByDate(
				DateUtil.convert("dd-MM-yyyy", "dd.MM.yyyy", date));

		if (!ratesList.isEmpty()) {
			log.info("Reads rates from database");
			return ratesList;
		}

		Gson gson = new Gson();
		Rates rates = gson.fromJson(new JsonReader(webResourceService.getResourceByURL(date)), Rates.class);

		ratesList = Arrays.asList(rates.getData());
		rateRepository.saveAll(ratesList);
		log.info("Reads rates from website");

		return ratesList;
	}
}
