package io.vasilizas.repositories.jpa;

import io.vasilizas.bean.db.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
}
