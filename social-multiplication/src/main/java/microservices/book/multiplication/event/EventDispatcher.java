package microservices.book.multiplication.event;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * author arks 18-May-2021
 * Handles communication with the Event Bus
 */
@Component
public class EventDispatcher {

    private RabbitTemplate rabbitTemplate;

    private String multiplicationExchange;

    private String multiplicationSolvedRoutingKey;

    @Autowired
    EventDispatcher(final RabbitTemplate pRabbitTemplate,
                    @Value("${multiplication.exchange}") final String pMultiplicationExchange,
                    @Value("${multiplication.solved.key}") final String pMultiplicationSolvedKey) {

        this.rabbitTemplate = pRabbitTemplate;
        this.multiplicationExchange = pMultiplicationExchange;
        this.multiplicationSolvedRoutingKey = pMultiplicationSolvedKey;
    }

    public void send(final MultiplicationSolvedEvent pMultiplicationSolvedEvent) {
        rabbitTemplate.convertAndSend(this.multiplicationExchange,
                this.multiplicationSolvedRoutingKey,
                pMultiplicationSolvedEvent);
    }
}
