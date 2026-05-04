package com.gym.subscription.entity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity @Table(name = "invitations") @Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Invitation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long requestedByMemberId;  // Member who made the request (null if admin-created)
    private String inviteeEmail;
    private String inviteeName;
    @Enumerated(EnumType.STRING) private Status status;
    private Long processedByAdminId;
    private LocalDateTime createdAt;
    private LocalDateTime processedAt;
    @PrePersist public void onCreate() { createdAt = LocalDateTime.now(); }
    public enum Status { PENDING, ACCEPTED, REJECTED }
}
