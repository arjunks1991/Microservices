package microservices.book.gamification.service;

import microservices.book.gamification.domain.LeaderBoardRow;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author arks 20-May-2021
 * Service to access Leaderboard with users and scores.
 */
public interface LeaderBoardService {

    /**
     * Retrieves the current leader board with the top score users
     * @return
     */
    List<LeaderBoardRow> getCurrentLeaderBoard();
}
