package com.gym.subscription.repository;
import com.gym.subscription.entity.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface InvitationRepository extends JpaRepository<Invitation, Long> {
    List<Invitation> findByStatus(Invitation.Status status);
    List<Invitation> findByRequestedByMemberId(Long memberId);
}
