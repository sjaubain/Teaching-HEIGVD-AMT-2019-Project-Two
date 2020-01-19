package ch.heigvd.movies.api.spec.steps;

import ch.heigvd.movies.ApiException;
import ch.heigvd.movies.api.dto.Rating;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertTrue;

/**
 * Test class relative to all http methods
 * on the different URLs
 */
public class NavigateSteps {

    private Context context;
    private PayloadSteps payloadSteps;

    public NavigateSteps(Context context, PayloadSteps payloadSteps) {
        this.context = context;
        this.payloadSteps = payloadSteps;
    }

    @When("I go to \\/movies\\/{int} path")
    public void i_go_to_movies_path(Integer id) {

        try {

            context.lastApiResponse = context.api.getRatingsByMovieWithHttpInfo(context.testToken, String.valueOf(id));
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

    @Then("I POST my rating to the \\/movies\\/{int} endpoint")
    public void i_POST_my_rating_to_the_movies_endpoint(Integer id) {

        try {

            context.lastApiResponse = context.api.addRatingWithHttpInfo(context.testToken,
                    String.valueOf(id), this.payloadSteps.rating);

            // save server response (the just posted rating)
            context.lastRating = (Rating)context.lastApiResponse.getData();

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

    @When("I DELETE my last rating to the movies\\/my-ratings endpoint")
    public void i_DELETE_my_last_rating_to_the_movies_endpoint() {

        try {
            context.lastApiResponse = context.api.removeRatingWithHttpInfo(context.testToken,
                    context.lastRating.getRatingId().toString());

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

    @When("I PUT my last rating to the movies\\/my-ratings endpoint with new description {string}")
    public void i_PUT_my_last_rating_to_the_movies_endpoint_with_new_description(String newDescription) {

        try {

            // update rating and save it to the last posted / put rating
            this.context.lastRating.setDescription(newDescription);

            context.lastApiResponse = context.api.updateRatingWithHttpInfo(context.testToken,
                    context.lastRating.getRatingId().toString(), this.context.lastRating);

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

    @Then("My last posted\\/put rating has been updated")
    public void my_last_posted_put_rating_has_been_updated() {

        // check if the updated rating is in the list obtained navigating in movies/{movieId}
        assertTrue(context.lastApiResponse.getData().toString().contains(context.lastRating.toString()));
    }

    @Then("I don't see my last posted\\/put rating")
    public void i_don_t_see_my_last_posted_put_rating() {
        assertTrue(!context.lastApiResponse.getData().toString().contains(context.lastRating.toString()));
    }

    @Then("I see my last posted\\/put rating")
    public void i_see_my_last_posted_put_rating() {
        assertTrue(context.lastApiResponse.getData().toString().contains(context.lastRating.toString()));
    }
}
