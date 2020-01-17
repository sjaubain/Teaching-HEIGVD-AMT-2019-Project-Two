package ch.heigvd.authentication.api.endpoints;

import ch.heigvd.authentication.api.AuthenticationApi;
import ch.heigvd.authentication.api.dto.User;
import ch.heigvd.authentication.api.dto.UserCredentials;
import ch.heigvd.authentication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthenticationController implements AuthenticationApi {


    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity<String> authenticate(UserCredentials userCredentials) {

        String password = userCredentials.getPassword();

        User user = userService.getUserByEmail(userCredentials.getEmail());

        if(userService.checkPassword(password, user)) {

            // generate a JWT and put it in the response body
            return ResponseEntity.ok(userService.generateJWT(user));

        } else {

            // answer with a 401 status code
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
