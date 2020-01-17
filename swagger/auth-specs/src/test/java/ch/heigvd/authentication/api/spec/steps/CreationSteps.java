package ch.heigvd.authentication.api.spec.steps;

import ch.heigvd.authentication.ApiException;
import ch.heigvd.authentication.api.dto.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertNotNull;

public class CreationSteps {

    private Context context;

    public CreationSteps(Context context) {
        this.context = context;
    }

    @Given("create a new user")
    public void create_a_new_user() {
        assertNotNull(context.api);
    }


    @Then("I get the new User to the \\/users\\/{string} path")
    public void i_get_the_new_User_to_the_users_path(String email) {
        try {
            context.lastApiResponse = context.api.getUserByEmailWithHttpInfo(context.lastToken, email);
            context.lastStatusCode = context.lastApiResponse.getStatusCode();
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    @When("I POST a new user {string} and passord {string} to the \\/users endpoint")
    public void i_POST_a_new_user_and_passord_to_the_users_endpoint(String email, String password) {
        try {

            // Get the admin token
            context.lastApiResponse = context.api.createUserWithHttpInfo(context.lastToken,
                    new User().email(email).password(password).isAdmin(false));
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
