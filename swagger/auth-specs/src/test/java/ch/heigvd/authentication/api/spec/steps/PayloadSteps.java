package ch.heigvd.authentication.api.spec.steps;

import ch.heigvd.authentication.api.dto.UserCredentials;
import ch.heigvd.authentication.api.spec.helpers.Environment;
import io.cucumber.java.en.Given;

public class PayloadSteps {

    Context context;
    UserCredentials userCredentials;

    public PayloadSteps(Environment environment, Context context) {
        this.context = context;
    }

    @Given("^I have valid user credentials$")
    public void i_have_valid_user_credentials() {
        userCredentials = new UserCredentials().email("simon.jobin@bluewin.ch").password("password");
    }

    @Given("I have invalid user credentials")
    public void i_have_invalid_user_credentials() {
        userCredentials = new UserCredentials().email("simon.jobin@bluewin.ch").password("invalid_password");
    }
}
