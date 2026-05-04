package com.gym.subscription.scheduler;

import com.gym.subscription.entity.Subscription;
import com.gym.subscription.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * Runs daily at midnight to auto-expire subscriptions whose end date has passed.
 */
@Component @RequiredArgsConstructor @Slf4j
public class SubscriptionExpiryScheduler {

    private final SubscriptionRepository subscriptionRepository;

    @Scheduled(cron = "0 0 0 * * *")
    @Transactional
    public void expireSubscriptions() {
        List<Subscription> expired = subscriptionRepository
                .findByStatusAndEndDateBefore(Subscription.Status.ACTIVE, LocalDate.now());
        expired.forEach(s -> s.setStatus(Subscription.Status.EXPIRED));
        subscriptionRepository.saveAll(expired);
        log.info("[SCHEDULER] Expired {} subscriptions", expired.size());
    }
}
