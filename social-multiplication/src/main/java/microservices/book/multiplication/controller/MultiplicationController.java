package microservices.book.multiplication.controller;

import microservices.book.multiplication.domain.Multiplication;
import microservices.book.multiplication.service.MultiplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * author arks 12-May-2021
 * Provides REST API to get random numbers for multiplication
 */
@RestController
@RequestMapping("/multiplications")
public class MultiplicationController {

    private final MultiplicationService multiplicationService;

    @Autowired
    public MultiplicationController(final MultiplicationService pMultiplicationService) {
        multiplicationService = pMultiplicationService;
    }

    @GetMapping("/random")
    public Multiplication getRandomMultiplication() {
        return multiplicationService.createRandomMultiplication();
    }
}
