package com.gym.subscription.entity;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity @Table(name = "plans") @Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Plan {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false) private String name;
    private String description;
    private Integer durationDays;
    @Column(nullable = false) private BigDecimal price;
    @Enumerated(EnumType.STRING) private Status status;
    public enum Status { ACTIVE, INACTIVE }
}
