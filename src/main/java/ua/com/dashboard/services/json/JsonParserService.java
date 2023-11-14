package ua.com.dashboard.services.json;

import java.io.Reader;

public interface JsonParserService<T> {
    T getJsonObject(Reader reader);
}
