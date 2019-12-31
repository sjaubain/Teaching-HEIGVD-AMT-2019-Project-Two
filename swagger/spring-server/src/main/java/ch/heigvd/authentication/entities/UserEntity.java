package ch.heigvd.authentication.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import org.mindrot.jbcrypt.BCrypt;

@Getter
@Setter
@Entity
@Table(name = "amt_user")
public class UserEntity implements Serializable {

    public UserEntity() {}

    public UserEntity(String email){
        this.email = email;
    }

    @Id
    @Column(name = "email")
    private String email;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "isAdmin")
    private Boolean isAdmin;

    @Column(name = "isLocked")
    private Boolean isLocked;

    /**
     * Store the encoded new user
     * password in order not to hard code it
     * in db. Used when creating or updating users
     * @param password password to encode
     */
    public void encodePassword(String password) {

        if (!password.isEmpty() || password != null) {

            // automatically generate salt
            this.password = BCrypt.hashpw(password, BCrypt.gensalt());
        }
    }
}
