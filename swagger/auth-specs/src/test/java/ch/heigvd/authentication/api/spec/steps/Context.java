package ch.heigvd.authentication.api.spec.steps;

import ch.heigvd.authentication.ApiException;
import ch.heigvd.authentication.ApiResponse;
import ch.heigvd.authentication.api.DefaultApi;
import ch.heigvd.authentication.api.dto.UserCredentials;
import ch.heigvd.authentication.api.spec.helpers.Environment;


/**
 * This class contains the context of the
 * testing client app, i.e last status code, last
 * API response... It is shared between all
 * other steps classes (with picocontainer)
 */
public class Context {

    Environment environment;
    DefaultApi api;

    ApiResponse lastApiResponse;
    ApiException lastApiException;
    boolean lastApiCallThrewException;
    int lastStatusCode;
    String lastToken;
    UserCredentials lastCredentials;

    public Context(Environment environment) {
        this.environment = environment;
        this.api = environment.getApi();
    }
}
