package com.gym.subscription.controller;
import com.gym.subscription.entity.Plan;
import com.gym.subscription.repository.PlanRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/plans") @RequiredArgsConstructor
@Tag(name = "Plans", description = "Subscription plan catalog")
public class PlanController {
    private final PlanRepository planRepository;

    @GetMapping
    public ResponseEntity<List<Plan>> getPlans() {
        return ResponseEntity.ok(planRepository.findByStatus(Plan.Status.ACTIVE));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Plan> createPlan(@RequestBody Plan plan) {
        plan.setStatus(Plan.Status.ACTIVE);
        return ResponseEntity.status(201).body(planRepository.save(plan));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deactivatePlan(@PathVariable Long id) {
        planRepository.findById(id).ifPresent(p -> { p.setStatus(Plan.Status.INACTIVE); planRepository.save(p); });
        return ResponseEntity.noContent().build();
    }
}
