package microservices.book.multiplication.service;

import microservices.book.multiplication.domain.Multiplication;
import microservices.book.multiplication.domain.MultiplicationResultAttempt;
import microservices.book.multiplication.domain.User;
import microservices.book.multiplication.event.EventDispatcher;
import microservices.book.multiplication.event.MultiplicationSolvedEvent;
import microservices.book.multiplication.repository.MultiplicationRepository;
import microservices.book.multiplication.repository.MultiplicationResultAttemptRepository;
import microservices.book.multiplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

/**
 * author arks 12-May-2021
 *
 */
@Service
public class MultiplicationServiceImpl implements MultiplicationService{

    private RandomGeneratorService randomGeneratorService;
    private UserRepository userRepository;
    private MultiplicationResultAttemptRepository multiplicationResultAttemptRepository;
    private MultiplicationRepository multiplicationRepository;
    private EventDispatcher eventDispatcher;

    @Autowired
    public MultiplicationServiceImpl(final RandomGeneratorService pRandomGeneratorService, final UserRepository pUserRepository,
                                     final MultiplicationResultAttemptRepository pMultiplicationResultAttemptRepository,
                                     final MultiplicationRepository pMultiplicationRepository,
                                     final EventDispatcher pEventDispatcher) {
        this.randomGeneratorService = pRandomGeneratorService;
        this.userRepository = pUserRepository;
        this.multiplicationResultAttemptRepository = pMultiplicationResultAttemptRepository;
        this.multiplicationRepository = pMultiplicationRepository;
        this.eventDispatcher = pEventDispatcher;
    }
    @Override
    public Multiplication createRandomMultiplication() {
        int factorA = this.randomGeneratorService.generateRandomFactor();
        int factorB = this.randomGeneratorService.generateRandomFactor();
        return new Multiplication(factorA, factorB);
    }

    @Override
    public boolean checkAttempt(MultiplicationResultAttempt multiplicationResultAttempt) {

        // Optional is java util class. In this case we are trying to find if the user sending the attempt is already
        // there in the DB. If it is there user object will have that specific user found by the user repository.
        // Else null will be stored in the user object.

        Optional<User> user = this.userRepository.findByAlias(multiplicationResultAttempt.getUser().getAlias());

        Optional<Multiplication> multiplication = this.multiplicationRepository.
                findMultiplicationByFactorAAndAndFactorA(multiplicationResultAttempt.getMultiplication().getFactorA(),
                        multiplicationResultAttempt.getMultiplication().getFactorB());
        boolean correct = multiplicationResultAttempt.getResultAttempt() ==
                multiplicationResultAttempt.getMultiplication().getFactorA() *
                        multiplicationResultAttempt.getMultiplication().getFactorB();

        Assert.isTrue(!multiplicationResultAttempt.isCorrect(), "Oh Oh!! You cant send an attempt marked as " +
                "Correct");

        //if user object is there it will be used. If it is null multiplicationResultAttempt.getUser() will be used.
        // Similar is the case with multiplication. If a multiplication entry is already there in the DB with the
        // given factor A and factor B, same multiplication will be used
        MultiplicationResultAttempt checkedAttempt = new MultiplicationResultAttempt(
                user.orElse(multiplicationResultAttempt.getUser()), multiplication.orElse(
                        multiplicationResultAttempt.getMultiplication()),
                multiplicationResultAttempt.getResultAttempt(), correct);

        this.multiplicationResultAttemptRepository.save(checkedAttempt);

        this.eventDispatcher.send(new MultiplicationSolvedEvent(checkedAttempt.getId(),
                checkedAttempt.getUser().getId(),
                checkedAttempt.isCorrect()));

        return correct;
    }

    @Override
    public List<MultiplicationResultAttempt> getStatsForUser(String alias) {
        return this.multiplicationResultAttemptRepository.findTop5ByUserAliasOrderByIdDesc(alias);
    }

    @Override
    public MultiplicationResultAttempt getResultById(Long resultId) {
        Optional<MultiplicationResultAttempt> attempt =  this.multiplicationResultAttemptRepository.findById(resultId);
        return attempt.orElse(null);
    }
}
