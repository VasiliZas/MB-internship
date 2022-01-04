package io.vasilizas.repositories.jpa;

import io.vasilizas.bean.db.MyClient;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<MyClient, String> {
}
