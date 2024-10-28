package com.javaweb.service;

import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBuildingService {
    List<BuildingSearchResponse> findAll(BuildingSearchRequest request, Pageable pageable);

    BuildingDTO findOneBuildingById(Long id);

    // Thêm mới hoặc cập nhật tòa nhà
    String addOrUpdateBuilding(BuildingDTO building);

    // Xóa các tòa nhà
    String deleteBuilding(Long[] listId);

    String updateAssignmentBuilding(AssignmentBuildingDTO assignmentBuildingDTO);

    int countTotalItems();
}
