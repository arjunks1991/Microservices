package microservices.book.multiplication.controller;

import microservices.book.multiplication.domain.MultiplicationResultAttempt;
import microservices.book.multiplication.service.MultiplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.ldap.PagedResultsResponseControl;
import java.util.List;
import java.util.Optional;

/**
 * author arks 12-May-2021
 * Provides REST API to POST the multiplication attempts from the users.
 */
@RestController
@RequestMapping("/results")
public class MultiplicationResultAttemptController {

    private final MultiplicationService multiplicationService;

    @Autowired
    public MultiplicationResultAttemptController(final MultiplicationService pMultiplicationService) {
        this.multiplicationService = pMultiplicationService;
    }

    @GetMapping
    ResponseEntity<List<MultiplicationResultAttempt>> getUserStats(@RequestParam("alias") String alias) {
        return ResponseEntity.ok(this.multiplicationService.getStatsForUser(alias));
    }

    @PostMapping
    ResponseEntity<MultiplicationResultAttempt> postResultResponseResponseEntity(@RequestBody MultiplicationResultAttempt multiplicationResultAttempt) {
        boolean isCorrect = multiplicationService.checkAttempt(multiplicationResultAttempt);
        MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(multiplicationResultAttempt.getUser(),
                multiplicationResultAttempt.getMultiplication(), multiplicationResultAttempt.getResultAttempt(),
                isCorrect);
        return ResponseEntity.ok(attempt);
    }

    @GetMapping("/{resultId}")
    public ResponseEntity<MultiplicationResultAttempt> getResultById(@PathVariable("resultId") Long resultId) {
        return ResponseEntity.ok(multiplicationService.getResultById(resultId));
    }
}
