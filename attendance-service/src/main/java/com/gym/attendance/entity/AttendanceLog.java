package com.gym.attendance.entity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity @Table(name = "attendance_logs") @Data @NoArgsConstructor @AllArgsConstructor @Builder
public class AttendanceLog {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false) private Long memberId;
    private Long checkedInByAdminId;
    private Long checkedOutByAdminId;
    private LocalDate attendanceDate;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
    @Enumerated(EnumType.STRING) private Status status;
    @PrePersist public void onCreate() { attendanceDate = LocalDate.now(); checkInTime = LocalDateTime.now(); }
    public enum Status { CHECKED_IN, CHECKED_OUT }
}
