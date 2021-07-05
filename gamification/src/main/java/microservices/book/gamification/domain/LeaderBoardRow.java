package microservices.book.gamification.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * author arks 19-May-2021
 *
 * Represents a row in the leaderboard. It links a user to a total score
 */
@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class LeaderBoardRow {
    private final Long userId;
    private final Long totalScore;

    public LeaderBoardRow() {
        this(0L, 0L);
    }
}
