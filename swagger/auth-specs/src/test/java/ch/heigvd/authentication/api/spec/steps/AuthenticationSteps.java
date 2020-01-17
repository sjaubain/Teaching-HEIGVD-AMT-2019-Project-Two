package ch.heigvd.authentication.api.spec.steps;

import ch.heigvd.authentication.ApiException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertNotNull;

public class AuthenticationSteps {

    private Context context;
    private PayloadSteps payloadSteps;

    public AuthenticationSteps(PayloadSteps payloadSteps, Context context) {
        this.context = context;
        this.payloadSteps = payloadSteps;
    }

    @Given("^try to authenticate$")
    public void try_to_authenticate() {
        // There should be a running server
        assertNotNull(context.api);
    }

    @When("^I POST it to the \\/authentication endpoint$")
    public void i_POST_it_to_the_authenticate_endpoint() {

        try {

            context.lastApiResponse = context.api.authenticateWithHttpInfo(payloadSteps.userCredentials);
            context.lastApiCallThrewException = false;
            context.lastApiException = null;
            context.lastStatusCode = context.lastApiResponse.getStatusCode();

        } catch (ApiException e) {

            context.lastApiCallThrewException = true;
            context.lastApiResponse = null;
            context.lastApiException = e;
            context.lastStatusCode = context.lastApiException.getCode();
        }
    }
}
