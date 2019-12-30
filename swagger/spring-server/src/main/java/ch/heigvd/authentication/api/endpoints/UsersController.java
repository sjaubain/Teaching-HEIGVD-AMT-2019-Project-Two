package ch.heigvd.authentication.api.endpoints;

import ch.heigvd.authentication.api.UsersApi;
import ch.heigvd.authentication.api.model.User;
import ch.heigvd.authentication.entities.UserEntity;
import ch.heigvd.authentication.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.models.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import springfox.documentation.swagger2.mappers.ModelMapper;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
public class UsersController implements UsersApi {

    private static final Logger log = LoggerFactory.getLogger(UsersController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private UserRepository userRepository;

    @org.springframework.beans.factory.annotation.Autowired
    public UsersController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }


    @Override
    public ResponseEntity<Object> createUser(User user) {
        UserEntity userEntity = toUserEntity(user);
        userRepository.save(userEntity);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{email}")
                .buildAndExpand(userEntity).toUri();

        return ResponseEntity.created(location).build();
    }

    @Override
    public ResponseEntity<User> getUserByEmail(String email) {

        UserEntity userEntity = userRepository.findById(email).get();

        if(userEntity == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        else
            return new ResponseEntity<>(toUser(userEntity), HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<List<User>> getUsers() {

        List<User> ret = new LinkedList<>();
        Iterable<UserEntity> allUsers = userRepository.findAll();

        for(UserEntity userEntity : allUsers) {
            ret.add(toUser(userEntity));
        }
        return new ResponseEntity<List<User>>(ret, HttpStatus.ACCEPTED);
    }

    /**
     * Convert DTO to Entity
     * @param user the user DTO
     * @return the user entity
     */
    private UserEntity toUserEntity(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(user.getEmail());
        userEntity.setFirstname(user.getFirstname());
        userEntity.setLastname(user.getLastname());
        userEntity.setPassword(user.getPassword());
        return userEntity;
    }

    /**
     * Convert Entity to DTO
     * @param userEntity the user entity
     * @return the user DTO
     */
    private User toUser(UserEntity userEntity) {
        User user = new User();
        user.setEmail(userEntity.getEmail());
        user.setFirstname(userEntity.getFirstname());
        user.setLastname(userEntity.getLastname());
        user.setPassword(userEntity.getPassword());
        return user;
    }
}
