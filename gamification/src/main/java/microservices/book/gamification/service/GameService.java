package microservices.book.gamification.service;

import microservices.book.gamification.domain.GameStats;

/**
 * author arks 20-May-2021
 * Service to handle all the business logic related to gamification
 */
public interface GameService {

    /**
     * Method to save the details when a new attempt has been made by the user
     * @param userId of the user who did the attempt
     * @param attemptId unique id of the attempt
     * @param isCorrect indicates if the attempt is correct or not
     * @return a {@Link GamesStats} object containing the new score and badge cards obtained
     */
    GameStats newAttemptByUser(Long userId, Long attemptId, boolean isCorrect);

    /**
     * Method to retrieve the stats corresponding to a user
     * @param userId of the specific user
     * @return a {@Link GamesStats} total statistics of the user
     */
    GameStats retrieveStatsForUser(Long userId);
}
