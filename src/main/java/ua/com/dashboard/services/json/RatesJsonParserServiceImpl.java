package ua.com.dashboard.services.json;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.springframework.stereotype.Service;
import ua.com.dashboard.view.rate.Rates;

import java.io.Reader;

@Service
public class RatesJsonParserServiceImpl implements JsonParserService<Rates> {

    @Override
    public Rates getJsonObject(Reader reader) {
        Gson gson = new Gson();
        return gson.fromJson(new JsonReader(reader), Rates.class);
    }
}
