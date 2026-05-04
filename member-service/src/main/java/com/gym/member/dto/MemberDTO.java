package com.gym.member.dto;

import lombok.*;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class MemberDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String status;
    private String joinedAt;
}
