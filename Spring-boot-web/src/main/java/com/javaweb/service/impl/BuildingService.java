package com.javaweb.service.impl;

import com.javaweb.converter.BuildingConverter;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.service.IBuildingService;
import com.javaweb.utils.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        BuildingEntity buildingEntity = buildingRepository.getOne(id);

        BuildingDTO buildingDTO = buildingConverter.convertToDTO(buildingEntity);

        return buildingDTO;
    }

    @Override
    public String addOrUpdateBuilding(BuildingDTO building) {
        BuildingEntity editBuilding = new BuildingEntity();
        String result = "";

        // Thêm mới thì ko cần id
        if (building.getId() == null) {
            editBuilding = modelMapper.map(building, BuildingEntity.class);

            editBuilding.setType(String.join(",", building.getTypeCode()));

            buildingRepository.save(editBuilding);

            // Khi này building mới thêm có id rồi, thì save rentArea vào
            // + Nếu save rentArea trước thì sẽ lỗi ngay :))
            for (String item : building.getRentArea().split(",")) {
                if (StringUtils.check(item)) {
                    RentAreaEntity rentAreaEntity = new RentAreaEntity();

                    rentAreaEntity.setBuildings(editBuilding);
                    rentAreaEntity.setValue(Integer.parseInt(item));

                    rentAreaRepository.save(rentAreaEntity);
                }
            }

            result = "Thêm mới thành công!";
        }
        // Cập nhật
        else {
            // Xóa các rentArea cũ để tránh bị trùng
            rentAreaRepository.deleteAllByBuildingEntity(buildingRepository.getOne(building.getId()));

            editBuilding = modelMapper.map(building, BuildingEntity.class);

            editBuilding.setType(String.join(",", building.getTypeCode()));

            buildingRepository.save(editBuilding);

            for (String item : building.getRentArea().split(",")) {
                if (StringUtils.check(item)) {
                    RentAreaEntity rentAreaEntity = new RentAreaEntity();

                    rentAreaEntity.setBuildings(editBuilding);
                    rentAreaEntity.setValue(Integer.parseInt(item));

                    rentAreaRepository.save(rentAreaEntity);
                }
            }

            return "Cập nhật thành công!";
        }

        return result;
    }

    // Xóa các building theo id
    @Override
    public String deleteBuilding(Long[] listId) {
        // Để xóa các building thì phải xóa chúng trong rentArea trước để tránh lỗi
        rentAreaRepository.deleteByBuildingEntityIn(buildingRepository.findByIdIn(listId));

        // Xóa
        buildingRepository.deleteByIdIn(listId);

        return "Xóa thành công!";
    }
}
