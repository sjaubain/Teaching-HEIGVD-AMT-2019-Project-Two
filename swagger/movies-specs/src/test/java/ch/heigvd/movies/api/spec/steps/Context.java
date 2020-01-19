package ch.heigvd.movies.api.spec.steps;

import ch.heigvd.movies.ApiException;
import ch.heigvd.movies.ApiResponse;
import ch.heigvd.movies.api.DefaultApi;
import ch.heigvd.movies.api.dto.Rating;
import ch.heigvd.movies.api.spec.helpers.Environment;

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

    // We use the same token and userId for all features
    // Cause we are not testing authentication features,
    // we consider that as the "background" for the tests
    String testToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9." +
            "eyJmaXJzdG5hbWUiOiJTaW1vbiIsImxhc3RuYW1lIjoiSm9iaW4iLCJ1c2VybmFtZSI6bnVsbCwiZW1haWwiOiJzaW1vbi5qb2JpbkB" +
            "ibHVld2luLmNoIiwicGFzc3dvcmQiOiIkMmEkMTAkVHdBdnhITUNscE1xMmNXQnZ2YjQ5ZUtvZ3JYbW1kZ2JYOWNOYWdnZE9kSUVRb0F" +
            "KL29nNTIiLCJpc0FkbWluIjp0cnVlLCJpc0xvY2tlZCI6ZmFsc2V9" +
            ".bJYnJg4F9z2c3S1TnV5yzpvw3UCTiUHhXm6ARLwMh6E=";

    String userId = "simon.jobin@bluewin.ch";

    // Last posted / put Rating (server answer to post / put methods,
    // to check if it has been correctly added or removed
    // in the movies/{movieId} page
    Rating lastRating;

    public Context(Environment environment) {
        this.environment = environment;
        this.api = environment.getApi();
    }
}