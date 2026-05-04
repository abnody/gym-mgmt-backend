package com.gym.subscription.repository;
import com.gym.subscription.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface PlanRepository extends JpaRepository<Plan, Long> {
    List<Plan> findByStatus(Plan.Status status);
}
