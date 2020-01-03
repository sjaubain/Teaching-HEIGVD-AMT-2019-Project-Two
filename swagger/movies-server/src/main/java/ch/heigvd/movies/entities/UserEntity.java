package ch.heigvd.movies.entities;

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
}
