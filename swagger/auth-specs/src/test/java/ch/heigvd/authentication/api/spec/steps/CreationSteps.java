package ch.heigvd.authentication.api.spec.steps;

import ch.heigvd.authentication.ApiException;
import ch.heigvd.authentication.ApiResponse;
import ch.heigvd.authentication.api.DefaultApi;
import ch.heigvd.authentication.api.dto.User;
import ch.heigvd.authentication.api.spec.helpers.Environment;

public class CreationSteps {

    private Environment environment;
    private DefaultApi api;

    User user;

    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private boolean lastApiCallThrewException;
    private int lastStatusCode;

    public CreationSteps(Environment environment) {
        this.environment = environment;
        this.api = environment.getApi();
    }
}
