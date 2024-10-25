package com.javaweb.service.impl;

import com.javaweb.converter.BuildingConverter;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.repository.AssignmentBuildingRepository;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.service.IBuildingService;
import com.javaweb.utils.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BuildingService implements IBuildingService {
    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private BuildingConverter buildingConverter;

    @Autowired
    private RentAreaRepository rentAreaRepository;

    @Autowired
    private AssignmentBuildingRepository assignmentBuildingRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<BuildingSearchResponse> findAll(BuildingSearchRequest buildingSearchRequest) {
        // Xuống tầng repo lấy các BuildingEntity
        List<BuildingEntity> buildingEntities = buildingRepository.findAll(buildingSearchRequest);

        // Xử lí đối tượng trả ra
        List<BuildingSearchResponse> buildingSearchResponses = new ArrayList<>();

        for (BuildingEntity item : buildingEntities) {
            buildingSearchResponses.add(buildingConverter.convertToSearchResponseDTO(item));
        }

        return buildingSearchResponses;
    }

    @Override
    public BuildingDTO findOneBuildingById(Long id) {
        BuildingEntity buildingEntity = buildingRepository.findOneBuildingById(id);

        BuildingDTO buildingDTO = buildingConverter.convertToDTO(buildingEntity);

        return buildingDTO;
    }

    @Override
    public String addOrUpdateBuilding(BuildingDTO building) {
        BuildingEntity editBuilding = new BuildingEntity();
        String result = "";

        if(building.getId() == null){
            result = "Thêm mới thành công!";
        }
        else{
            editBuilding = buildingRepository.getOne( building.getId() );

            // Xóa các rentArea cũ để tránh bị trùng
            rentAreaRepository.deleteAllByBuildingEntity(editBuilding);

            result = "Cập nhật thành công!";
        }

        editBuilding = modelMapper.map(building, BuildingEntity.class);

        editBuilding.setType(String.join(",", building.getTypeCode()));

        buildingRepository.save(editBuilding);

        for (String it : building.getRentArea().split(",")) {
            if (StringUtils.check(it)) {
                RentAreaEntity rentAreaEntity = new RentAreaEntity();

                rentAreaEntity.setBuildingEntity(editBuilding);
                rentAreaEntity.setValue(Integer.parseInt(it));

                rentAreaRepository.save(rentAreaEntity);
            }
        }

        return result;
    }

    // Xóa các building theo id
    @Override
    public String deleteBuilding(Long[] listId) {
        List<BuildingEntity> buildingEntities = buildingRepository.findByIdIn(listId);

        // Để xóa các building thì phải xóa chúng trong bảng rentarea trước để tránh lỗi
        rentAreaRepository.deleteByBuildingEntityIn(buildingEntities);

        // Cũng cần phải xóa trong assignmentbuilding nữa
        assignmentBuildingRepository.deleteAllByBuildingsIn(buildingEntities);

        // Xóa
        buildingRepository.deleteByIdIn(listId);

        return "Xóa thành công!";
    }
}
