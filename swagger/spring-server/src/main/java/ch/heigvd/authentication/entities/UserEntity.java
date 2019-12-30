package ch.heigvd.authentication.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

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
}
