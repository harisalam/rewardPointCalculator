package dev.haris.assignment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.YearMonth;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RewardResult {
    private Long customerID;
    private Map<YearMonth, Double> pointsPerMonth;
    private Double totalPoints;
}
