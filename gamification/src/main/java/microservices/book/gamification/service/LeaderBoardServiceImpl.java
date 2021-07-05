package microservices.book.gamification.service;

import microservices.book.gamification.domain.LeaderBoardRow;
import microservices.book.gamification.repository.ScoreCardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author arks 21-May-2021
 * Service Implementation to handle all the business logic related to LeaderBoard
 */
@Service
public class LeaderBoardServiceImpl implements  LeaderBoardService{
    private ScoreCardRepository scoreCardRepository;

    public LeaderBoardServiceImpl(final ScoreCardRepository pScoreCardRepository) {
        this.scoreCardRepository = pScoreCardRepository;
    }

    @Override
    public List<LeaderBoardRow> getCurrentLeaderBoard() {
        return this.scoreCardRepository.findFirst10();
    }
}
