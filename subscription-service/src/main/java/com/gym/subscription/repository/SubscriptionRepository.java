package com.gym.subscription.repository;
import com.gym.subscription.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findByMemberId(Long memberId);
    Optional<Subscription> findByMemberIdAndStatus(Long memberId, Subscription.Status status);
    List<Subscription> findByStatusAndEndDateBefore(Subscription.Status status, LocalDate date);
    long countByStatus(Subscription.Status status);
}
