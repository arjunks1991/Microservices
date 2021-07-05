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
 * Class linking a badge to a user. Also has the timestamp when the user got the badge.
 */
@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
@Entity
public class BadgeCard {

    @Id
    @GeneratedValue
    @Column(name="BADGE_ID")
    private final Long badgeId;
    private final Badge badge;
    private final Long userId;
    private final long badgeTimestamp;

    // Empty constructor for serialization and JPA
    public BadgeCard() {
        this(null, null, null, 0);
    }

    public BadgeCard(final Badge badge, final Long userId) {
        this(null, badge, userId, System.currentTimeMillis());
    }
}
