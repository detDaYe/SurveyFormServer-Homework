package org.lumenk.object.webserver.repositories;

import org.lumenk.object.webserver.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
}
