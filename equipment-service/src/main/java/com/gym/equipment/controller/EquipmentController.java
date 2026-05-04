package com.gym.equipment.controller;
import com.gym.equipment.entity.Equipment;
import com.gym.equipment.service.EquipmentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/equipment") @RequiredArgsConstructor
@Tag(name = "Equipment", description = "Gym equipment inventory management")
public class EquipmentController {
    private final EquipmentService equipmentService;

    @GetMapping
    public ResponseEntity<List<Equipment>> getAll() {
        return ResponseEntity.ok(equipmentService.getAll());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Equipment> add(
            @RequestBody Equipment equipment,
            @RequestHeader("X-User-Id") Long adminId) {
        return ResponseEntity.status(201).body(equipmentService.add(equipment, adminId));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> remove(
            @PathVariable Long id,
            @RequestHeader("X-User-Id") Long adminId) {
        equipmentService.remove(id, adminId);
        return ResponseEntity.noContent().build();
    }
}
