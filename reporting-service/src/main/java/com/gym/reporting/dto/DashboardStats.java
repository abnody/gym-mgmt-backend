package com.gym.reporting.dto;
import lombok.*;
import java.math.BigDecimal;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class DashboardStats {
    private long totalMembers;
    private long activeMembers;
    private long activeSubscriptions;
    private BigDecimal monthlyRevenue;
    private BigDecimal totalRevenue;
    private long currentCapacity;   // from attendance service
}
