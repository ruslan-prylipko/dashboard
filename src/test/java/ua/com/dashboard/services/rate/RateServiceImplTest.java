package ua.com.dashboard.services.rate;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.com.dashboard.repositories.rate.RateRepository;
import ua.com.dashboard.services.json.JsonParserService;
import ua.com.dashboard.services.resource.WebResourceService;
import ua.com.dashboard.view.rate.Rate;
import ua.com.dashboard.view.rate.Rates;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RateServiceImplTest {

    private String date = "09-11-2023";
    private String dbDate = "09.11.2023";

    @Mock
    RateRepository rateRepository;
    @Mock
    JsonParserService<Rates> jsonParserService;
    @Mock
    WebResourceService webResourceService;
    @InjectMocks
    RateServiceImpl rateService;

    @Test
    @SneakyThrows
    void getAllRatesFromDataBaseWhenCorrectDate() {

        List<Rate> rateList = Arrays.asList(
                new Rate(17, "978", "EUR", "євро", "38.4518", "-0.0322", "09.11.2023"),
                new Rate(53, "840", "USD", "Долар США", "36.0407", "0.0036", "09.11.2023")
        );

        doReturn(rateList).when(this.rateRepository).findAllByDate(dbDate);

        List<Rate> allRates = rateService.getAllRates(date);

        assertNotNull(allRates);
        assertEquals(rateList, allRates);
    }

    @Test
    @SneakyThrows
    void getAllRatesFromWebSiteWhenCorrectDate() {

        Rate[] rateArray = new Rate[]{
                new Rate(17, "978", "EUR", "євро", "38.4518", "-0.0322", "09.11.2023"),
                new Rate(53, "840", "USD", "Долар США", "36.0407", "0.0036", "09.11.2023")
        };

        Rates rates = new Rates();
        rates.setData(rateArray);

        Reader reader = new InputStreamReader(new InputStream() {
            @Override
            public int read() {
                return 0;
            }
        });

        doReturn(List.of()).when(this.rateRepository).findAllByDate(dbDate);
        doReturn(reader).when(this.webResourceService).getResourceByURL(date);
        doReturn(rates).when(this.jsonParserService).getJsonObject(reader);

        List<Rate> allRates = rateService.getAllRates(date);

        assertNotNull(allRates);
        assertEquals(Arrays.asList(rateArray), allRates);
    }

    @Test
    void saveAllRates() {

        List<Rate> rateList = Arrays.asList(
                new Rate(17, "978", "EUR", "євро", "38.4518", "-0.0322", "09.11.2023"),
                new Rate(53, "840", "USD", "Долар США", "36.0407", "0.0036", "09.11.2023")
        );

        doReturn(rateList).when(this.rateRepository).saveAll(rateList);

        assertEquals(rateList, this.rateService.saveAllRates(rateList));

         verify(this.rateRepository).saveAll(rateList);
    }
}