package dev.haris.assignment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RewardService {
    private static final Logger logger = LoggerFactory.getLogger(RewardService.class);
    public List<RewardResult> calculateRewardPoints(List<Transaction> transactions) {
        LocalDate currentDate = LocalDate.now();
        YearMonth endDate = YearMonth.from(currentDate);
        YearMonth startDate = endDate.minusMonths(2);
        Map<Long, Map<YearMonth, Double>> customerPointsMap = new HashMap<>();

        for (Transaction transaction : transactions) {
            YearMonth transactionMonth = YearMonth.from(transaction.getTransactionDate());
            if (transactionMonth.isBefore(startDate) || transactionMonth.isAfter(endDate)) {
                continue;
            }
            double points = calculatePoints(transaction.getTransactionAmount());
            customerPointsMap
                    .computeIfAbsent(transaction.getCustomerId(), k -> new HashMap<>())
                    .merge(transactionMonth, points, Double::sum);
        }

        List<RewardResult> rewardPointsResults = new ArrayList<>();
        customerPointsMap.forEach((customerId, monthPointsMap) -> {
            double totalPoints = monthPointsMap.values().stream().mapToDouble(Double::doubleValue).sum();
            RewardResult result = new RewardResult(customerId, monthPointsMap, totalPoints);
            rewardPointsResults.add(result);
        });
        return rewardPointsResults;
    }


    private double calculatePoints(double transactionAmount) {
        double points = 0.0;
        if (transactionAmount > 100) {
            points += (transactionAmount - 100) * 2;
            transactionAmount = 100;
        }

        if (transactionAmount > 49 && transactionAmount < 101) {
            points += (transactionAmount - 50);
        }
        return points;
    }
}

