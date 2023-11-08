package ua.com.dashboard.controllers.rate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import ua.com.dashboard.services.rate.RateService;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Controller
@RequestMapping("/dashboard/rates")
public class RateController {
	
    private final RateService rateService;

    @Autowired
    public RateController(RateService rateService) {
        this.rateService = rateService;
    }

    @GetMapping("/all")
    public String getAllRates(@RequestParam("date") String date, Model model)
            throws JsonIOException, JsonSyntaxException, IOException, ParseException {
        model.addAttribute("rates", rateService.getRates(reverse(date)));
        return "dashboard";
    }
    
    private static String reverse(String date) throws ParseException {
    	SimpleDateFormat inputDate = new SimpleDateFormat("yyyy-MM-dd");
    	SimpleDateFormat outputDate = new SimpleDateFormat("dd-MM-yyyy");
    	return outputDate.format(inputDate.parse(date));
	}
}