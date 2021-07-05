package microservices.book.gamification.controller;

import microservices.book.gamification.domain.GameStats;
import microservices.book.gamification.service.GameService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * author arks 21-May-2021
 *
 * This class implements the REST API for the Gamification User stats Service
 */
@RestController
@RequestMapping("/stats")
public class UserStatsController {
    private GameService gameService;

    public UserStatsController(final GameService pGameService) {
        this.gameService = pGameService;
    }

    @GetMapping
    public GameStats getGameStatsForUser(@RequestParam("userId") final Long pUserId) {
        return this.gameService.retrieveStatsForUser(pUserId);
    }
}
