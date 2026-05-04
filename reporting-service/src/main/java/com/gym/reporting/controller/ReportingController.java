package com.gym.reporting.controller;
import com.gym.reporting.dto.DashboardStats;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Reporting service aggregates data via internal REST calls to other services.
 * Each microservice exposes an /internal/stats endpoint for this purpose.
 */
@RestController @RequestMapping("/reports")
@RequiredArgsConstructor
@Tag(name = "Reporting", description = "Dashboard analytics and accounting reports - Admin only")
public class ReportingController {

    // In a full implementation, this service calls member-service, subscription-service,
    // attendance-service via RestTemplate / Feign Client with lb:// URLs

    @GetMapping("/dashboard")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DashboardStats> getDashboard() {
        // TODO: Aggregate from member-service, subscription-service, attendance-service
        return ResponseEntity.ok(DashboardStats.builder().build());
    }

    @GetMapping("/revenue")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getRevenue(
            @RequestParam(required = false) String from,
            @RequestParam(required = false) String to) {
        // TODO: Call subscription-service for financial transactions filtered by date range
        return ResponseEntity.ok().build();
    }
}
