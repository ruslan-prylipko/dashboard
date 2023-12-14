package ua.com.dashboard.controllers.rate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import ua.com.dashboard.services.rate.RateService;
import ua.com.dashboard.utils.DateUtil;
import ua.com.dashboard.utils.Util;

import java.io.IOException;
import java.text.ParseException;

@Controller
@RequestMapping("/rates")
public class RateController {

    @Value("${api.minfin.com.ua.rates}")
    private String baseURL;

    private final RateService rateService;

    @Autowired
    public RateController(RateService rateService) {
        this.rateService = rateService;
    }

    @GetMapping("/all")
    public String getAllRates(@RequestParam("date") String date, Model model)
            throws JsonIOException, JsonSyntaxException, IOException, ParseException {
        Util.isMatchingToPattern("\\d{4}-\\d{2}-\\d{2}", date);
        model.addAttribute("rates", rateService.getAllRates(baseURL,
                DateUtil.convert("yyyy-MM-dd", "dd-MM-yyyy", date)));
        return "dashboard";
    }
}
