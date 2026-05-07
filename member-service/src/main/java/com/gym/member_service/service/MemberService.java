package com.gym.member_service.service;

import com.gym.member_service.dto.MemberRequestDTO;
import com.gym.member_service.dto.MemberResponseDTO;
import java.util.List;

public interface MemberService {

    MemberResponseDTO createMember(MemberRequestDTO request);

    MemberResponseDTO getMemberById(Long id);

    List<MemberResponseDTO> getAllMembers();

    MemberResponseDTO updateMember(Long id, MemberRequestDTO request);

    void deleteMember(Long id);
}
