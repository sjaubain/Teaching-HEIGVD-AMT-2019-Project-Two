package ch.heigvd.authentication.api.endpoints;

import ch.heigvd.authentication.api.UsersApi;
import ch.heigvd.authentication.api.dto.User;
import ch.heigvd.authentication.entities.UserEntity;
import ch.heigvd.authentication.repositories.UserRepository;
import ch.heigvd.authentication.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.LinkedList;
import java.util.List;

@Controller
public class UserController implements UsersApi {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private UserService userService;

    @org.springframework.beans.factory.annotation.Autowired
    public UserController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @Override
    public ResponseEntity<Object> createUser(String authorization, User user) {

        User postingUser = userService.decodeJWT(authorization);

        // check if user has the rights to create a new one (is admin)
        if(postingUser != null && postingUser.getIsAdmin()) {

            userService.createUser(user);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{email}")
                    .buildAndExpand(user).toUri();

            return ResponseEntity.created(location).build();

        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @Override
    public ResponseEntity<User> getUserByEmail(String authorization, String email) {

        if(authorization == null || email == null)
            return ResponseEntity.noContent().build();

        User askingUser = userService.decodeJWT(authorization);

        if(askingUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // check if it is the user's token
        User user = userService.getUserByEmail(email);

        if(user != null) {

            // if the user owns the information or if he is an admin
            if (user.getEmail().equals(askingUser.getEmail()) || user.getIsAdmin()) {
                return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @Override
    public ResponseEntity<List<User>> getUsers() {

        List<User> ret = userService.getAllUsers();

        return new ResponseEntity<>(ret, HttpStatus.ACCEPTED);
    }
}
