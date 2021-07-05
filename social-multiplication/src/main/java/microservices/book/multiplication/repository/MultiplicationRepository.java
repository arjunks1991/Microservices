package microservices.book.multiplication.repository;

import microservices.book.multiplication.domain.Multiplication;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


/**
 * author arks 13-May-2021
 *
 * Repository used for CRUD operation of Multiplication entity. This allows us to
 * store and retrieve multiplications
 * CrudRepository is an interface provided by Spring to implement CRUD operations. We just need to
 * pass as parameters the class which is annotated with @Entity and the identifier type
 */
public interface MultiplicationRepository extends CrudRepository<Multiplication, Long> {
    /**
     * This method returns the multiplication object if there is any with the provided factorA and factorB
     * combination. We just need to use a corresponding method exposed by the CrudRepository
     * @param factorA
     * @param factorB
     * @return
     */
    Optional<Multiplication> findMultiplicationByFactorAAndAndFactorA(final int factorA, final int factorB);
}
