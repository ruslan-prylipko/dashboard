package ua.com.dashboard.services.json;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import ua.com.dashboard.view.rate.Rates;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class RatesJsonParserServiceImplTest {

    private String rateJSON = """
                {
                    "data": [
                        {
                             "iso": 978,
                             "code": "EUR",
                             "name": "євро",
                             "rate": 39.3054,
                             "trend": -0.0885,
                             "date": "17.11.2023",
                             "type": "daily"
                        },
                        {
                            "iso": 840,
                            "code": "USD",
                            "name": "Долар США",
                            "rate": 36.2496,
                            "trend": -0.0147,
                            "date": "17.11.2023",
                            "type": "daily"
                        }
                    ]
                }
                """;

    @Test
    @SneakyThrows
    void getJsonObjectCorrectData() {


        StringReader reader = new StringReader(rateJSON);

        Gson gson = new Gson();
        Rates rates = gson.fromJson(new JsonReader(reader), Rates.class);

        JsonParserService<Rates> jsonParserService = new RatesJsonParserServiceImpl();

        reader.reset();
        assertNotNull(jsonParserService.getJsonObject(reader));

        reader.reset();
        assertEquals(rates, jsonParserService.getJsonObject(reader));
    }

    @Test
    @SneakyThrows
    void getJsonObjectIncorrectData() {

        StringReader reader = new StringReader("""
                "data": [
                        {
                             "iso": 978,
                             "code": "EUR",
                             "name": "євро",
                             "rate": 39.3054,
                             "trend": -0.0885,
                             "date": "17.11.2023",
                             "type": "daily"
                        }
                """);

        JsonParserService<Rates> jsonParserService = new RatesJsonParserServiceImpl();

        assertThrows(JsonSyntaxException.class, () -> jsonParserService.getJsonObject(reader));
    }
}