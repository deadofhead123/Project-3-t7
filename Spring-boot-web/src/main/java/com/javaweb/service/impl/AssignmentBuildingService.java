package com.javaweb.service.impl;

import com.javaweb.entity.AssignmentBuildingEntity;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.repository.AssignmentBuildingRepository;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.IAssignmentBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AssignmentBuildingService implements IAssignmentBuildingService {
    @Autowired
    BuildingRepository buildingRepository;

    @Autowired
    AssignmentBuildingRepository assignmentBuildingRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public String updateAssignmentBuilding(AssignmentBuildingDTO assignmentBuildingDTO) {
        // Tìm tòa nhà
        BuildingEntity buildingEntity = buildingRepository.getOne(assignmentBuildingDTO.getBuildingId());

        // Xóa phần giao cũ
        assignmentBuildingRepository.deleteAllByBuildings(buildingEntity);

        // Lưu phần giao mới
        for(Long item : assignmentBuildingDTO.getStaffIds()){
            AssignmentBuildingEntity assignmentBuildingEntity = new AssignmentBuildingEntity();

            assignmentBuildingEntity.setUsers(userRepository.getOne(item));
            assignmentBuildingEntity.setBuildings(buildingEntity);

            assignmentBuildingRepository.save(assignmentBuildingEntity);
        }

        return "Cập nhật giao tòa nhà thành công!";
    }
}
