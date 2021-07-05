package microservices.book.multiplication.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * author arks 12-May-2021
 *
 * Stores information about the multiplication result and the user who posted it.
 */
@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
@Entity
public final class MultiplicationResultAttempt {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="USER_ID")
    private final User user;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="MULTIPLICATION_ID")
    private final Multiplication multiplication;
    private final int resultAttempt;
    private final boolean correct;

    //Empty constructor for JSON (de) serialization
    public MultiplicationResultAttempt() {
        user = null;
        multiplication = null;
        resultAttempt = -1;
        correct = false;
    }
}
