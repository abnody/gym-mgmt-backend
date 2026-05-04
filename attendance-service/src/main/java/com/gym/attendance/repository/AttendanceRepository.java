package com.gym.attendance.repository;
import com.gym.attendance.entity.AttendanceLog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
public interface AttendanceRepository extends JpaRepository<AttendanceLog, Long> {
    List<AttendanceLog> findByMemberId(Long memberId);
    List<AttendanceLog> findByAttendanceDate(LocalDate date);
    Optional<AttendanceLog> findByMemberIdAndAttendanceDateAndStatus(Long memberId, LocalDate date, AttendanceLog.Status status);
    long countByAttendanceDateAndStatus(LocalDate date, AttendanceLog.Status status);
}
