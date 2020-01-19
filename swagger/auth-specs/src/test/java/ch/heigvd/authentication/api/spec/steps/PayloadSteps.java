package ch.heigvd.authentication.api.spec.steps;

import ch.heigvd.authentication.api.dto.UserCredentials;
import io.cucumber.java.en.Given;

public class PayloadSteps {

    Context context;

    public PayloadSteps(Context context) {
        this.context = context;
    }

    @Given("^I have valid user credentials$")
    public void i_have_valid_user_credentials() {
        context.lastCredentials = new UserCredentials().email("simon.jobin@bluewin.ch").password("password");
    }

    @Given("I have invalid user credentials")
    public void i_have_invalid_user_credentials() {
        context.lastCredentials = new UserCredentials().email("simon.jobin@bluewin.ch").password("invalid_password");
    }

    @Given("^I have valid admin credentials$")
    public void i_have_valid_admin_credentials() {
        context.lastCredentials = new UserCredentials().email("simon.jobin@bluewin.ch").password("password");
    }
}
