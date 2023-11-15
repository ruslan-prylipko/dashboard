package ua.com.dashboard.services.rate;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.dashboard.repositories.rate.RateRepository;
import ua.com.dashboard.services.json.JsonParserService;
import ua.com.dashboard.services.resource.WebResourceService;
import ua.com.dashboard.utils.DateUtil;
import ua.com.dashboard.view.rate.Rate;
import ua.com.dashboard.view.rate.Rates;

import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.util.*;

@Service
@Log4j2
public class RateServiceImpl implements RateService {

	private final RateRepository rateRepository;
	private final JsonParserService<Rates> jsonParserService;
	private final WebResourceService webResourceService;

	@Autowired
	public RateServiceImpl(RateRepository rateRepository, JsonParserService<Rates> jsonParserService,
						   WebResourceService webResourceService) {
		this.rateRepository = rateRepository;
		this.jsonParserService = jsonParserService;
		this.webResourceService = webResourceService;
	}

	@Override
	public List<Rate> getAllRates(final String date)
			throws JsonIOException, JsonSyntaxException, IOException, ParseException {

		String dbDate = DateUtil.convert("dd-MM-yyyy", "dd.MM.yyyy", date);
		List<Rate> ratesList = rateRepository.findAllByDate(dbDate);

		if (!ratesList.isEmpty()) {
			log.info("Reads rates from database");
			return ratesList;
		}

		Reader reader = webResourceService.getResourceByURL(date);
		Rates rates = jsonParserService.getJsonObject(reader);

		ratesList = Arrays.asList(rates.getData());
		this.saveAllRates(ratesList);

		log.info("Reads rates from website");

		return ratesList;
	}

	public Iterable<Rate> saveAllRates(final List<Rate> rates) {
		return rateRepository.saveAll(rates);
	}
}
