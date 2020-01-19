package ch.heigvd.movies.api.spec.steps;

import ch.heigvd.movies.api.dto.Rating;
import io.cucumber.java.en.Given;

public class PayloadSteps {

    private Context context;
    Rating rating;

    public PayloadSteps(Context context) {
        this.context = context;
    }

    @Given("I want to rate a movie with description {string} and rate {int}")
    public void i_want_to_rate_a_movie_with_description_and_rate(String description, Integer rate) {
        rating = new Rating().userId(context.userId).rating(rate).description(description);
    }
}
