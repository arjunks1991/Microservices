package microservices.book.gamification.repository;

import microservices.book.gamification.domain.BadgeCard;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * author arks 20-May-2021
 *
 * This class handles all CRUD operations related to badgeCards.
 */
public interface BadgeCardRepository extends CrudRepository<BadgeCard, Long> {
    /**
     * Retrieves all BadgeCards corresponding to a particular user
     * @param userId of the user
     * @return the list of all badge earned by a user, sorted by most recent.
     */
    List<BadgeCard> findByUserIdOrderByBadgeTimestampDesc(final Long userId);
}
