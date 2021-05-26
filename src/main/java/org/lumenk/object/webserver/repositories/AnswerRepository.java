package org.lumenk.object.webserver.repositories;

import org.lumenk.object.webserver.entities.Answer;
import org.lumenk.object.webserver.entities.Form;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends CrudRepository<Answer, Long> {

}
