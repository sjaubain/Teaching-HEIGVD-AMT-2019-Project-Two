package ch.heigvd.authentication.api.endpoints;

import ch.heigvd.authentication.api.UsersApi;
import ch.heigvd.authentication.api.dto.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
public class UsersController implements UsersApi {

    private static final Logger log = LoggerFactory.getLogger(UsersController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public UsersController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }


    @Override
    public ResponseEntity<Object> createUser(User user) {
        return null;
    }

    @Override
    public ResponseEntity<List<User>> getUsers() {
        try {
            return new ResponseEntity<List<User>>(objectMapper.readValue("[]", List.class), HttpStatus.ACCEPTED);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
