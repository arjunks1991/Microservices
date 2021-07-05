package microservices.book.gamification.controller;

import microservices.book.gamification.domain.LeaderBoardRow;
import microservices.book.gamification.service.LeaderBoardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * author arks 21-May-2021
 *
 * This class implements the REST API for the Gamification LeaderBoard Service
 */
@RestController
@RequestMapping("/leaders")
public class LeaderBoardController {
    private LeaderBoardService leaderBoardService;

    public LeaderBoardController(final LeaderBoardService pLeaderBoardService) {
        this.leaderBoardService = pLeaderBoardService;
    }

    @GetMapping
    public List<LeaderBoardRow> getLeaderBoard() {
        return this.leaderBoardService.getCurrentLeaderBoard();
    }
}
