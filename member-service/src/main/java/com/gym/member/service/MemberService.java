package com.gym.member.service;

import com.gym.member.dto.MemberDTO;
import com.gym.member.entity.Member;
import com.gym.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public List<MemberDTO> getAllMembers() {
        return memberRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public MemberDTO getMemberById(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found with id: " + id));
        return toDTO(member);
    }

    public void deactivateMember(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found"));
        member.setStatus(Member.Status.DEACTIVATED);
        memberRepository.save(member);
    }

    // Called internally when a user registers via auth-service (via Feign or event)
    public MemberDTO createMemberProfile(MemberDTO dto) {
        Member member = Member.builder()
                .id(dto.getId())
                .name(dto.getName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .status(Member.Status.ACTIVE)
                .build();
        return toDTO(memberRepository.save(member));
    }

    private MemberDTO toDTO(Member m) {
        return MemberDTO.builder()
                .id(m.getId())
                .name(m.getName())
                .email(m.getEmail())
                .phone(m.getPhone())
                .status(m.getStatus() != null ? m.getStatus().name() : null)
                .joinedAt(m.getJoinedAt() != null ? m.getJoinedAt().toString() : null)
                .build();
    }
}
