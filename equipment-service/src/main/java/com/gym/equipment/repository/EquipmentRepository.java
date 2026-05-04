package com.gym.equipment.repository;
import com.gym.equipment.entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    List<Equipment> findByStatusNot(Equipment.Status status);
}
