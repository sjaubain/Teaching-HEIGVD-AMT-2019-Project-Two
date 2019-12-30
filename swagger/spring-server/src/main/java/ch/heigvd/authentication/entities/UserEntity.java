package ch.heigvd.authentication.entities;

import javax.persistence.*;
import java.io.Serializable;

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

    public String getEmail() {
        return email;
    }
}
