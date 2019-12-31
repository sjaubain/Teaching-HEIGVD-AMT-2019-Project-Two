package ch.heigvd.authentication.services;

import ch.heigvd.authentication.api.dto.User;
import ch.heigvd.authentication.entities.UserEntity;

import java.util.List;

public interface IUserService {

    boolean checkPassword(String givenPassword, User user);

    void createUser(User user);

    User getUserByEmail(String email);

    List<User> getAllUsers();

    String generateJWT(User userClaims);

    UserEntity toUserEntity(User user);

    User toUser(UserEntity userEntity);
}
