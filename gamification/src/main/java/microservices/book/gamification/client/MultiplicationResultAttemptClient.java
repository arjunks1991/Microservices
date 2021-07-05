package microservices.book.gamification.client;

/**
 * author arks 26-May-2021
 *
 * Used to connect to Multiplication microservice.
 */
public interface MultiplicationResultAttemptClient {
    MultiplicationResultAttempt retrieveMultiplicationResultAttemptById(final Long attemptId);
}
