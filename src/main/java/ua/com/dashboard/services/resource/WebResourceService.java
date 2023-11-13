package ua.com.dashboard.services.resource;

import java.io.IOException;
import java.io.Reader;

public interface WebResourceService {

    Reader getResourceByURL(final String url) throws IOException;
}
