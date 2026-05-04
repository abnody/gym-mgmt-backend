package com.gym.attendance.service;
import com.gym.attendance.entity.AttendanceLog;
import com.gym.attendance.repository.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service @RequiredArgsConstructor
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;

    public AttendanceLog checkIn(Long memberId, Long adminId) {
        AttendanceLog log = AttendanceLog.builder()
                .memberId(memberId)
                .checkedInByAdminId(adminId)
                .status(AttendanceLog.Status.CHECKED_IN)
                .build();
        return attendanceRepository.save(log);
    }

    public AttendanceLog checkOut(Long memberId, Long adminId) {
        AttendanceLog log = attendanceRepository
                .findByMemberIdAndAttendanceDateAndStatus(memberId, LocalDate.now(), AttendanceLog.Status.CHECKED_IN)
                .orElseThrow(() -> new RuntimeException("No active check-in found for member"));
        log.setStatus(AttendanceLog.Status.CHECKED_OUT);
        log.setCheckOutTime(LocalDateTime.now());
        log.setCheckedOutByAdminId(adminId);
        return attendanceRepository.save(log);
    }

    public List<AttendanceLog> getMemberAttendance(Long memberId) {
        return attendanceRepository.findByMemberId(memberId);
    }

    public List<AttendanceLog> getTodayAttendance() {
        return attendanceRepository.findByAttendanceDate(LocalDate.now());
    }

    public long getCurrentCapacity() {
        return attendanceRepository.countByAttendanceDateAndStatus(LocalDate.now(), AttendanceLog.Status.CHECKED_IN);
    }
}
