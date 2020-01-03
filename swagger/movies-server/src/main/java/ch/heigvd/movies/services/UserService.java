package ch.heigvd.movies.services;

import ch.heigvd.movies.api.dto.User;
import ch.heigvd.movies.entities.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{

    //TODO: share it with the second API
    private static final String SECRET_KEY = "secret";


    /**
     * Decode the given JWT and check it
     * @param jwt the jwt
     * @return the User corresponding
     * to the jwt
     */
    @Override
    public User decodeJWT(String jwt) {

       return null;
    }

    /**
     * Convert DTO to Entity
     * @param user the user DTO
     * @return the user entity
     */
    public UserEntity toUserEntity(User user) {
        //TODO: use a mapper would be cleaner
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(user.getEmail());
        return userEntity;
    }

    /**
     * Convert Entity to DTO
     * @param userEntity the user entity
     * @return the user DTO
     */
    public User toUser(UserEntity userEntity) {
        //TODO: use a mapper would be cleaner
        User user = new User();
        user.setEmail(userEntity.getEmail());
        return user;
    }
}
