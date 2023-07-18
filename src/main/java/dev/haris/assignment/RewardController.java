package dev.haris.assignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class RewardController{
    private final RewardService rewardService;
    @Autowired
    private RewardController(RewardService rewardService) {
        this.rewardService = rewardService;
    }
    @PostMapping
    private List<RewardResult> calculateRewardPoints(@RequestBody List<Transaction> transactions) {
        return rewardService.calculateRewardPoints(transactions);
    }
}
