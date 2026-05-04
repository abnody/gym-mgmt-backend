package com.gym.equipment.entity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity @Table(name = "equipment") @Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Equipment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false) private String name;
    private String category;
    private Integer quantity;
    @Enumerated(EnumType.STRING) private Status status;
    private Long addedByAdminId;
    private LocalDateTime addedAt;
    private LocalDateTime removedAt;
    @PrePersist public void onCreate() { addedAt = LocalDateTime.now(); }
    public enum Status { AVAILABLE, UNDER_MAINTENANCE, REMOVED }
}
