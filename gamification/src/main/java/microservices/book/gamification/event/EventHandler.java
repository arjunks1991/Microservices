package microservices.book.gamification.event;

import microservices.book.gamification.service.GameService;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * author arks 24-May-2021
 *
 * This class receives the event and triggers the associated business logic
 */
@Component
public class EventHandler {
    private GameService gameService;

    EventHandler(final GameService gameService) {
        this.gameService = gameService;
    }

    @RabbitListener(queues = "${multiplication.queue}")
    void handleMultiplicationSolved(final MultiplicationSolvedEvent event) {
        try {
            gameService.newAttemptByUser(event.getUserId(), event.getMultiplicationResultAttemptId(), event.isCorrect());
        } catch (final Exception e) {
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }
}
