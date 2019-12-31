package ch.heigvd.authentication.services;

import ch.heigvd.authentication.api.dto.User;
import ch.heigvd.authentication.entities.UserEntity;
import ch.heigvd.authentication.repositories.UserRepository;
import com.auth0.jwt.algorithms.Algorithm;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.LinkedList;
import java.util.List;

@Service
public class UserService implements IUserService{

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

    @Override
    public String generateJWT(User userClaims) {

        Algorithm algorithm = Algorithm.HMAC256("secret");

        // jwt header
        byte[] header = Base64.getEncoder().encode("{\"alg\":\"HMAC256\",\"typ\":\"JWT\"}".getBytes());

        // jwt claims
        byte[] claims = Base64.getEncoder().encode(userClaims.toString().getBytes());

        return "accessToken " + algorithm.sign(header, claims).toString();
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
