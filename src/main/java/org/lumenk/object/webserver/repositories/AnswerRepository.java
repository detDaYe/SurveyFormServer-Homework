package org.lumenk.object.webserver.repositories;

import org.lumenk.object.webserver.entities.Answer;
import org.springframework.data.repository.CrudRepository;

public interface AnswerRepository extends CrudRepository<Answer, Long> {
}
