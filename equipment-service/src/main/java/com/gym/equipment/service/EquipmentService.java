package com.gym.equipment.service;
import com.gym.equipment.entity.Equipment;
import com.gym.equipment.repository.EquipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service @RequiredArgsConstructor
public class EquipmentService {
    private final EquipmentRepository equipmentRepository;

    public List<Equipment> getAll() {
        return equipmentRepository.findByStatusNot(Equipment.Status.REMOVED);
    }

    public Equipment add(Equipment equipment, Long adminId) {
        equipment.setStatus(Equipment.Status.AVAILABLE);
        equipment.setAddedByAdminId(adminId);
        return equipmentRepository.save(equipment);
    }

    public void remove(Long id, Long adminId) {
        Equipment e = equipmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipment not found"));
        e.setStatus(Equipment.Status.REMOVED);
        e.setRemovedAt(LocalDateTime.now());
        equipmentRepository.save(e);
    }
}
