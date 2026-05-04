package com.gym.subscription.entity;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity @Table(name = "subscriptions") @Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Subscription {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false) private Long memberId;
    @ManyToOne @JoinColumn(name = "plan_id") private Plan plan;
    private LocalDate startDate;
    private LocalDate endDate;
    @Enumerated(EnumType.STRING) private Status status;
    private BigDecimal amountPaid;
    private LocalDateTime createdAt;
    @PrePersist public void onCreate() { createdAt = LocalDateTime.now(); }
    public enum Status { ACTIVE, EXPIRED, TERMINATED }
}
