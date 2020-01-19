package ch.heigvd.movies.api.spec.steps;

import io.cucumber.java.en.Then;

import static org.junit.Assert.assertEquals;

public class ResponseSteps {

    private Context context;

    public ResponseSteps(Context context) {
        this.context = context;
    }

    @Then("I receive a {int} status code")
    public void i_receive_a_status_code(int arg) {
        assertEquals(arg, context.lastStatusCode);
    }
}
