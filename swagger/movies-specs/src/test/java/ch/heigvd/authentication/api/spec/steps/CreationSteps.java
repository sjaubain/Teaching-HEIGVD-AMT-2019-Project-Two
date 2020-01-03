package ch.heigvd.authentication.api.spec.steps;

import ch.heigvd.authentication.ApiException;
import ch.heigvd.authentication.ApiResponse;
import ch.heigvd.authentication.api.DefaultApi;
import ch.heigvd.authentication.api.dto.User;
import ch.heigvd.authentication.api.spec.helpers.Environment;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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

    @Given("^there is a User server$")
    public void there_is_a_User_server() {
        assertNotNull(api);
    }

    @Given("^I have a user payload$")
    public void i_have_a_user_payload() throws Throwable {
        user = new ch.heigvd.authentication.api.dto.User();
    }

    @When("I POST it to the \\/users endpoint with invalid token")
    public void i_POST_it_to_the_authenticate_endpoint() throws Throwable {

        try {

            lastApiResponse = api.createUserWithHttpInfo("invalidToken", user.username("sjaubain"));
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

    @Then("^I receive a (\\d+) status code$")
    public void i_receive_a_status_code(int arg1) throws Throwable {
        assertEquals(arg1, lastStatusCode);
    }
}
