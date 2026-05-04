package com.gym.member.controller;

import com.gym.member.dto.MemberDTO;
import com.gym.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
@Tag(name = "Members", description = "Member management - Admin only")
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get all members (Admin only)")
    public ResponseEntity<List<MemberDTO>> getAllMembers() {
        return ResponseEntity.ok(memberService.getAllMembers());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get member profile by ID")
    public ResponseEntity<MemberDTO> getMember(@PathVariable Long id) {
        return ResponseEntity.ok(memberService.getMemberById(id));
    }

    @PutMapping("/{id}/deactivate")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Deactivate a member account (Admin only)")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        memberService.deactivateMember(id);
        return ResponseEntity.noContent().build();
    }

    // Internal endpoint: called by auth-service after registration
    @PostMapping("/internal/create")
    @Operation(hidden = true)
    public ResponseEntity<MemberDTO> createProfile(@RequestBody MemberDTO dto) {
        return ResponseEntity.ok(memberService.createMemberProfile(dto));
    }
}
