package ch.heigvd.authentication.services;

import ch.heigvd.authentication.api.dto.User;
import ch.heigvd.authentication.entities.UserEntity;
import ch.heigvd.authentication.repositories.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.util.LinkedList;
import java.util.List;

@Service
public class UserService implements IUserService{

    //TODO: share it with the second API
    private static final String SECRET_KEY = "secret";

    @Autowired
    private UserRepository userRepository;

    /**
     * Check if the given password corresponds
     * to the one stored in the userentity
     * @param givenPassword
     * @param user
     * @return
     */
    @Override
    public boolean checkPassword(String givenPassword, User user) {
        return BCrypt.checkpw(givenPassword, user.getPassword());
    }

    @Override
    public void createUser(User user) {

        // save the new user with his encoded password
        UserEntity userEntity = toUserEntity(user);
        userEntity.encodePassword(user.getPassword());
        userRepository.save(userEntity);
    }

    @Override
    public User getUserByEmail(String email) {
        return toUser(userRepository.findById(email).get());
    }

    @Override
    public List<User> getAllUsers() {
        List<User> ret = new LinkedList<>();
        Iterable<UserEntity> allUsers = userRepository.findAll();
        for(UserEntity userEntity : allUsers) {
            ret.add(toUser(userEntity));
        }
        return ret;
    }

    /**
     * Generate a Json Web Token according to
     * specifications with the user as claim payload
     * @param userClaims User info to be stringify
     * @return the Json Web Token
     */
    @Override
    public String generateJWT(User userClaims) {

        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

        try {

            // jwt header
            String header = DatatypeConverter.printBase64Binary("{\"alg\":\"HS256\",\"typ\":\"JWT\"}".getBytes());

            // jwt claims
            ObjectMapper objectMapper = new ObjectMapper();

            // stringify user object
            String userToJson = objectMapper.writeValueAsString(userClaims);
            String claims = DatatypeConverter.printBase64Binary(userToJson.getBytes());

            // signature
            String signature = DatatypeConverter.printBase64Binary(algorithm.sign(header.getBytes(), claims.getBytes()));

            return "accessToken : " + header + "." + claims + "." + signature;

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Decode the given JWT and check it
     * @param jwt the jwt
     * @return the User corresponding
     * to the jwt
     */
    @Override
    public User decodeJWT(String jwt) {

        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT tokenJWT = verifier.verify(jwt);

        return toUser(userRepository.findById(tokenJWT.getClaim("email").asString()).get());
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
        userEntity.setFirstname(user.getFirstname());
        userEntity.setLastname(user.getLastname());
        userEntity.setPassword(user.getPassword());
        userEntity.setIsAdmin(user.getIsAdmin());
        userEntity.setIsLocked(user.getIsLocked());
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
        user.setFirstname(userEntity.getFirstname());
        user.setLastname(userEntity.getLastname());
        user.setPassword(userEntity.getPassword());
        user.setIsAdmin(userEntity.getIsAdmin());
        user.setIsLocked(userEntity.getIsLocked());
        return user;
    }
}
