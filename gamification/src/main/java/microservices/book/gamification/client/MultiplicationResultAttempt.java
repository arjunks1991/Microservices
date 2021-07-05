package microservices.book.gamification.client;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * author arks 26-May-2021
 *
 * Class used to identify a result attempted by a user
 */
@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
@JsonDeserialize(using = MultiplicationResultAttemptDeserializer.class)
public final class MultiplicationResultAttempt {

    private final String userAlias;
    private final int multiplicationFactorA;
    private final int multiplicationFactorB;
    private final int resultAttempt;
    private final boolean correct;

    MultiplicationResultAttempt() {
        this.userAlias = null;
        this.multiplicationFactorA = -1;
        this.multiplicationFactorB = -1;
        this.resultAttempt = -1;
        this.correct = false;
    }
}
