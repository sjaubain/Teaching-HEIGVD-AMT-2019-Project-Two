package ch.heigvd.authentication.api.spec.steps;
import io.cucumber.java.en.Then;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ResponseSteps {

    private Context context;

    public ResponseSteps(Context context) {
        this.context = context;
    }

    @Then("I receive a {int} status code and a jwt")
    public void i_receive_a_status_code_and_a_jwt(int arg) {

        assertEquals(arg, context.lastStatusCode);
        assertTrue(context.lastApiResponse.getData().toString().startsWith("accessToken"));
        context.lastToken = context.lastApiResponse.getData().toString().substring(14);
    }

    @Then("I receive a {int} status code")
    public void i_receive_a_status_code(int arg) {
        assertEquals(arg, context.lastStatusCode);
    }

}
