package microservices.book.multiplication.service;

import microservices.book.multiplication.domain.Multiplication;
import microservices.book.multiplication.domain.MultiplicationResultAttempt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * author arks 12-May-2021
 */
public interface MultiplicationService {
    /**
     * Creates a Multiplication object with two randomly-generated factors
     *  between 11 and 99.
     *
     * @return a Multiplication object with random factors
     */
    Multiplication createRandomMultiplication();
    /**
     * Checks if the multiplication attempt is right or not
     *
     * @return true if the attempt is correct else will return false
     */
    boolean checkAttempt(MultiplicationResultAttempt multiplicationResultAttempt);
    /**
     * Method to return the previous attempts of the user
     *
     * @return list of MultiplicationResultAttempt for a corresponding user.
     */
    List<MultiplicationResultAttempt> getStatsForUser(String alias);
    /**
     * Method to return a particular attempt based on the id passed
     *
     * @return MultiplicationResultAttempt object having Id as the one passed as the argument.
     */
    MultiplicationResultAttempt getResultById(Long resultId);
}
