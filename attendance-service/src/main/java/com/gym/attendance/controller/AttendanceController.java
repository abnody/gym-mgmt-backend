package com.gym.attendance.controller;
import com.gym.attendance.entity.AttendanceLog;
import com.gym.attendance.service.AttendanceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/attendance") @RequiredArgsConstructor
@Tag(name = "Attendance", description = "Member check-in/out and attendance logs")
public class AttendanceController {
    private final AttendanceService attendanceService;

    @PostMapping("/checkin/{memberId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AttendanceLog> checkIn(
            @PathVariable Long memberId,
            @RequestHeader("X-User-Id") Long adminId) {
        return ResponseEntity.ok(attendanceService.checkIn(memberId, adminId));
    }

    @PostMapping("/checkout/{memberId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AttendanceLog> checkOut(
            @PathVariable Long memberId,
            @RequestHeader("X-User-Id") Long adminId) {
        return ResponseEntity.ok(attendanceService.checkOut(memberId, adminId));
    }

    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<AttendanceLog>> getMemberLogs(@PathVariable Long memberId) {
        return ResponseEntity.ok(attendanceService.getMemberAttendance(memberId));
    }

    @GetMapping("/today")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<AttendanceLog>> getToday() {
        return ResponseEntity.ok(attendanceService.getTodayAttendance());
    }

    @GetMapping("/capacity")
    public ResponseEntity<Long> getCapacity() {
        return ResponseEntity.ok(attendanceService.getCurrentCapacity());
    }
}
