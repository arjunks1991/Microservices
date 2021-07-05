package microservices.book.multiplication.repository;

import microservices.book.multiplication.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * author arks 13-May-2021
 *
 * Repository used for CRUD operation of User entity. This allows us to
 * store and retrieve users
 * CrudRepository is an interface provided by Spring to implement CRUD operations. We just need to
 * pass as parameters the class which is annotated with @Entity and the identifier type
 */
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByAlias(final String alias);
}
