package microservices.book.gamification.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * author arks 19-May-2021
 *
 * This object contains the result of one or more iterations of the game.
 * It may contain any number of combinations of {@Link ScoreCard} objects and {@Link BadgeCard} objects.
 * It can be used to determine the total number of badges or total score earned by a user.
 */
@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class GameStats {

    private final Long userId;
    private final int score;
    private final List<Badge> badges;

    public GameStats() {
        this.userId = 0L;
        this.score = 0;
        this.badges = new ArrayList<>();
    }

    /**
     * Factory method to return an empty game stats object with 0 score and 0 badges
     * @param userId
     * @return empty GameStats object
     */

    public static GameStats getEmptyStats(final Long userId) {
        return new GameStats(userId, 0, Collections.emptyList());
    }

    /**
     * @return Unmodifiable view of the badge cards list
     */
    public List<Badge> getBadges() {
        return Collections.unmodifiableList(this.badges);
    }
}
