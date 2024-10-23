package com.javaweb.repository;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentAreaRepository extends JpaRepository<RentAreaEntity, Long> {
    void deleteAllByBuildingEntity(BuildingEntity buildingEntity);

    // Xóa hết các rentArea có building liên quan
    void deleteByBuildingEntityIn(List<BuildingEntity> buildingEntity);
}
