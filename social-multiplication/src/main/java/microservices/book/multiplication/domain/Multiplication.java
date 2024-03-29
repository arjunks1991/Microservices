package microservices.book.multiplication.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * author arks 12-May-2021
 *
 * This class represents a Multiplication (a * b)
 */
@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Entity
public final class Multiplication {
    @Id
    @GeneratedValue
    @Column(name = "MULTIPLICATION_ID")
    private Long Id;

    // Both factors
    private final int factorA;
    private final int factorB;

    // Empty Constructor for JSON (de)serialization
    protected Multiplication() {
        this(0,0);
    }
}
