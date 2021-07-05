package microservices.book.gamification.service;

import microservices.book.gamification.client.MultiplicationResultAttempt;
import microservices.book.gamification.client.MultiplicationResultAttemptClient;
import microservices.book.gamification.domain.Badge;
import microservices.book.gamification.domain.BadgeCard;
import microservices.book.gamification.domain.GameStats;
import microservices.book.gamification.domain.ScoreCard;
import microservices.book.gamification.repository.BadgeCardRepository;
import microservices.book.gamification.repository.ScoreCardRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * author arks 21-May-2021
 * Service Implementation to handle all the business logic related to gamification
 */
@Service
public class GameServiceImpl implements GameService{
    public static final int LUCKY_NUMBER = 4;
    private ScoreCardRepository scoreCardRepository;
    private BadgeCardRepository badgeCardRepository;

    private MultiplicationResultAttemptClient attemptClient;

    public GameServiceImpl(ScoreCardRepository pScoreCardRepository, BadgeCardRepository pBadgeCardRepository,
                           MultiplicationResultAttemptClient pMultiplicationResultAttemptClient) {
        this.scoreCardRepository = pScoreCardRepository;
        this.badgeCardRepository = pBadgeCardRepository;
        this.attemptClient = pMultiplicationResultAttemptClient;
    }
    @Override
    public GameStats newAttemptByUser(final Long pUserId, final Long pAttemptId, final boolean pIsCorrect) {

        if (pIsCorrect) {
            ScoreCard scoreCard = new ScoreCard(pUserId, pAttemptId);
            scoreCardRepository.save(scoreCard);
            List<BadgeCard> badgeCards = processForBadges(pUserId, pAttemptId);

        }
        return null;
    }

    @Override
    public GameStats retrieveStatsForUser(Long pUserId) {
        int userTotalScore = this.scoreCardRepository.getTotalScoreForUser(pUserId);
        List<BadgeCard> userBadgeCards = this.badgeCardRepository.findByUserIdOrderByBadgeTimestampDesc(pUserId);
        return new GameStats(pUserId, userTotalScore, userBadgeCards.stream().map(BadgeCard::getBadge).collect(Collectors.toList()));
    }

    private List<BadgeCard> processForBadges(final Long pUserId, final Long pAttemptId) {
        List<BadgeCard> badgeCards = new ArrayList<>();
        int totalScore = this.scoreCardRepository.getTotalScoreForUser(pUserId);
        List<ScoreCard> scoreCardsList = this.scoreCardRepository.findByUserIdOrderByScoreTimestampDesc(pUserId);
        List<BadgeCard> badgeCardsList = this.badgeCardRepository.findByUserIdOrderByBadgeTimestampDesc(pUserId);

        // Assign badges depending on the score
        checkAndGiveBadgeBasedOnScore(badgeCardsList, Badge.BRONZE_MULTIPLICATOR, totalScore, 100, pUserId).ifPresent(badgeCards::add);

        checkAndGiveBadgeBasedOnScore(badgeCardsList, Badge.SILVER_MULTIPLICATOR, totalScore, 500, pUserId).ifPresent(badgeCards::add);

        checkAndGiveBadgeBasedOnScore(badgeCardsList, Badge.GOLD_MULTIPLICATOR, totalScore, 999, pUserId).ifPresent(badgeCards::add);

        // First Badge to won
        if (scoreCardsList.size() == 1 && !containsBadge(badgeCardsList, Badge.FIRST_WON)) {
            BadgeCard firstBadgeWon = giveBadgeToUser(Badge.FIRST_WON, pUserId);
            badgeCards.add(firstBadgeWon);
        }

        //Lucky Number Badge
        MultiplicationResultAttempt multiplicationResultAttempt = this.attemptClient.retrieveMultiplicationResultAttemptById(pAttemptId);
        if(!containsBadge(badgeCardsList, Badge.LUCKY_NUMBER) && (LUCKY_NUMBER == multiplicationResultAttempt.getMultiplicationFactorA() ||
                LUCKY_NUMBER == multiplicationResultAttempt.getMultiplicationFactorB())) {
            BadgeCard luckyNumberBadge = giveBadgeToUser(Badge.LUCKY_NUMBER, pUserId);
            badgeCards.add(luckyNumberBadge);
        }
        return badgeCards;
    }

    /**
     * Method to check if the passed badge list has a particular bage
     * @param pBadgeCards
     * @param badge
     * @return
     */
    private boolean containsBadge(final List<BadgeCard> pBadgeCards, final Badge badge) {
        return pBadgeCards.stream().anyMatch(badgeCard -> badgeCard.getBadge().equals(badge));
    }

    /**
     * Method to save a BadgeCard object for a particular user and a badge
     * @param pBadge
     * @param pUserId
     * @return
     */
    private BadgeCard giveBadgeToUser(final Badge pBadge, final Long pUserId) {
        BadgeCard badgeCard = new BadgeCard(pBadge, pUserId);
        this.badgeCardRepository.save(badgeCard);
        return badgeCard;
    }

    /**
     * Checks the current score of the user against respective thresholds to assign Badges.
     * @param pBadgeCards
     * @param pBadge
     * @param pScore
     * @param pSoreThreshold
     * @param pUserId
     * @return
     */
    private Optional<BadgeCard> checkAndGiveBadgeBasedOnScore(
            final List<BadgeCard> pBadgeCards, final Badge pBadge, final int pScore, final int pSoreThreshold, final Long pUserId) {
        if (pScore >= pSoreThreshold && !containsBadge(pBadgeCards, pBadge)) {
            return Optional.of(giveBadgeToUser(pBadge, pUserId));
        }
        return Optional.empty();
    }
}
