package ch.heigvd.movies.services;

import ch.heigvd.movies.api.dto.User;
import ch.heigvd.movies.entities.UserEntity;

import java.util.List;

public interface IUserService {

    User decodeJWT(String jwt);

    UserEntity toUserEntity(User user);

    User toUser(UserEntity userEntity);
}
