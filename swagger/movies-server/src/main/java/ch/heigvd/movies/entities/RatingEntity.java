package ch.heigvd.movies.entities;

import lombok.Getter;
import lombok.Setter;

import javax.annotation.Generated;
import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "amt_rating")
public class RatingEntity {

    public RatingEntity() {}

    public RatingEntity(String userId, int movieId, int rating, String description) {
        this.userId = userId;
        this.movieId = movieId;
        this.rating = rating;
        this.description = description;
    }

    @Id
    @Column(name = "rating_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ratingId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "movie_id")
    private Integer movieId;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "description")
    private String description;
}
