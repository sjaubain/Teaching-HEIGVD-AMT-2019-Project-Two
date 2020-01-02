package ch.heigvd.authentication.api.spec.steps;

import ch.heigvd.authentication.ApiException;
import ch.heigvd.authentication.ApiResponse;
import ch.heigvd.authentication.api.DefaultApi;
import ch.heigvd.authentication.api.dto.UserCredentials;
import ch.heigvd.authentication.api.spec.helpers.Environment;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class AuthenticationSteps {

    private Environment environment;
    private DefaultApi api;

    UserCredentials userCredentials;

    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private boolean lastApiCallThrewException;
    private int lastStatusCode;

    public AuthenticationSteps(Environment environment) {
        this.environment = environment;
        this.api = environment.getApi();
    }

    @Given("^try to authenticate$")
    public void try_to_authenticate() {
        assertNotNull(api);
    }

    @Given("^I have user credentials$")
    public void i_have_user_credentials() {
        userCredentials = new UserCredentials().email("simon.jobin@bluewin.ch").password("password");
    }

    @When("^I POST it to the \\/authenticate endpoint$")
    public void i_POST_it_to_the_authenticate_endpoint() {

        try {

            lastApiResponse = api.authenticateWithHttpInfo(userCredentials);
            lastApiCallThrewException = false;
            lastApiException = null;
            lastStatusCode = lastApiResponse.getStatusCode();

        } catch (ApiException e) {

            lastApiCallThrewException = true;
            lastApiResponse = null;
            lastApiException = e;
            lastStatusCode = lastApiException.getCode();
        }
    }

    @Then("I receive a {int} status code and a jwt")
    public void i_receive_a_status_code_and_a_jwt(int arg1) {

        assertEquals(lastStatusCode, arg1);
        assertTrue(lastApiResponse.getData().toString().startsWith("accessToken"));
    }

}
