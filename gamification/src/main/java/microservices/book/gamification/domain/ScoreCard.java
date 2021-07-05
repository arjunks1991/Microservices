package microservices.book.gamification.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * author arks 19-May-2021
 *
 * Class representing the score linked to an attempt performed by the user. It also includes the timestamp in
 * which the score is registered
 */
@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
@Entity
public class ScoreCard {

    // Default score assigned to this card, if not specified
    private static final int DEFAULT_SCORE = 0;
    @Id
    @GeneratedValue
    @Column(name="CARD_ID")
    private final Long cardId;

    @Column(name="USER_ID")
    private final Long userId;

    @Column(name="ATTEMPT_ID")
    private final Long attemptId;

    @Column(name="SCORE_TS")
    private final long scoreTimestamp;

    @Column(name="SCORE")
    private final int score;

    // Empty constructor required for serializer and JPA
    public ScoreCard() {
        this(null, null, null, 0, 0);
    }

    public ScoreCard(final Long userId, final Long attemptId) {
        this(null, userId, attemptId, System.currentTimeMillis(), ScoreCard.DEFAULT_SCORE);
    }
}
