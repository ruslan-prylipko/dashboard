package ua.com.dashboard.services.resource;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.*;
import java.net.URLConnection;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WebResourceServiceImplTest {

    private String baseURL = "http://example.com/";
    @Mock(strictness = Mock.Strictness.LENIENT)
    URLConnection urlConnection;
    @Mock(strictness = Mock.Strictness.LENIENT)
    InputStream inputStream;
    @InjectMocks
    WebResourceServiceImpl webResourceService;

    @Test
    @SneakyThrows
    void getResourceByURLCorrectURL() {

        doReturn(inputStream).when(this.urlConnection).getInputStream();

        assertInstanceOf(Reader.class, webResourceService.getResourceByURL(baseURL));
    }

    @Test
    @SneakyThrows
    void getResourceByURLIncorrectURL() {

        doReturn(inputStream).when(this.urlConnection).getInputStream();

        assertThrows(IOException.class, () -> webResourceService.getResourceByURL("baseURL"));
    }
}