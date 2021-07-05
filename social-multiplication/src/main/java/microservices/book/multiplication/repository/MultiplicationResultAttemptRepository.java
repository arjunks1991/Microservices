package microservices.book.multiplication.repository;

import microservices.book.multiplication.domain.MultiplicationResultAttempt;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * author arks 13-May-2021
 *
 * Repository used for CRUD operation of MultiplicationResultAttempt entity. This allows us to
 * store and retrieve attempts
 * CrudRepository is an interface provided by Spring to implement CRUD operations. We just need to
 * pass as parameters the class which is annotated with @Entity and the identifier type
 */
public interface MultiplicationResultAttemptRepository extends CrudRepository<MultiplicationResultAttempt, Long> {

    /**
     * This method returns the last 5 user attempts identified by the user alias.
     * @param userAlias
     * @return
     */
    public List<MultiplicationResultAttempt> findTop5ByUserAliasOrderByIdDesc(String userAlias);
}
