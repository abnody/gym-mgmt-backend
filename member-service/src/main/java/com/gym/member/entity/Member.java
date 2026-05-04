package com.gym.member.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

/**
 * Mirrors the User entity from auth-service.
 * Member service holds profile-level data while auth-service holds credentials.
 * Synced via userId (the auth service's user ID is the source of truth).
 */
@Entity
@Table(name = "members")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Member {

    @Id
    private Long id;   // Same ID as auth-service User (no auto-generation here)

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    private String phone;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime joinedAt;

    @PrePersist
    public void onCreate() { this.joinedAt = LocalDateTime.now(); }

    public enum Status { ACTIVE, DEACTIVATED }
}
