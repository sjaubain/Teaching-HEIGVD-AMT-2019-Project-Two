package ch.heigvd.authentication.api.spec.helpers;

import ch.heigvd.authentication.api.DefaultApi;

import java.io.IOException;
import java.util.Properties;


public class Environment {

    private DefaultApi api = new DefaultApi();

    public Environment() throws IOException {
        Properties properties = new Properties();
        properties.load(this.getClass().getClassLoader().getResourceAsStream("environment.properties"));
        String url = properties.getProperty("ch.heigvd.authentication.server.url");
        api.getApiClient().setBasePath(url);

    }

    public DefaultApi getApi() {
        return api;
    }


}
