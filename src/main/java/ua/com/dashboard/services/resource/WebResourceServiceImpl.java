package ua.com.dashboard.services.resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

@Service
public class WebResourceServiceImpl implements WebResourceService {

    @Value("${api.minfin.com.ua.rates}")
    private String baseURL;

    @Override
    public Reader getResourceByURL(String url) throws IOException {
        return new InputStreamReader(new URL(baseURL + url).openConnection().getInputStream());
    }
}
