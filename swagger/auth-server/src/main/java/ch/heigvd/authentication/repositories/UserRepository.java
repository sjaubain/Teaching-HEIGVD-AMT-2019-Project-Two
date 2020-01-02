package ch.heigvd.authentication.repositories;

import ch.heigvd.authentication.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<UserEntity, String> {

}
