package com.javaweb.service.impl;

import com.javaweb.converter.BuildingConverter;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.IBuildingService;
import com.javaweb.utils.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<BuildingSearchResponse> findAll(BuildingSearchRequest buildingSearchRequest, Pageable pageable) {
        // Xuống tầng repo lấy các BuildingEntity
        List<BuildingEntity> buildingEntities = buildingRepository.findAll(buildingSearchRequest, pageable);

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
        // - Khi chưa sử dụng Cascade, muốn xóa các building có id cần truyền vào
        //   ta phải xóa building trong bảng rentarea và assignmentbuilding trước vì 2 bảng này có tham chiếu tới building, rồi mới xóa các building đó trong bảng id
        // - Giờ đã dùng Cascade thì nó sẽ làm sẵn việc xóa building trong bảng rentarea và assignmentbuilding trước
        // Xóa
        List<BuildingEntity> buildingEntities = buildingRepository.findByIdIn(listId);

        rentAreaRepository.deleteByBuildingEntityIn(buildingEntities);

        List<UserEntity> userEntities = userRepository.findByStatusAndRoles_Code(1, "STAFF");

        for(UserEntity userEntity : userEntities){
            List<BuildingEntity> buildingEntities1 = userEntity.getBuildings();

            buildingEntities1.removeAll(buildingEntities);

            userEntity.setBuildings(buildingEntities1);

            userRepository.save(userEntity);
        }
        
        buildingRepository.deleteByIdIn(listId);

        return "Xóa thành công!";
    }

    @Override
    public String updateAssignmentBuilding(AssignmentBuildingDTO assignmentBuildingDTO) {
        BuildingEntity buildingEntity = buildingRepository.getOne(assignmentBuildingDTO.getBuildingId());

        // Xóa phần giao cũ đi để tránh bị trùng
        List<UserEntity> userEntities = userRepository.findByStatusAndRoles_Code(1, "STAFF");
        List<UserEntity> userEntityAssigning = userRepository.findByIdIn(assignmentBuildingDTO.getStaffIds());

        for(UserEntity userEntity : userEntities){
            List<BuildingEntity> buildingEntities = userEntity.getBuildings();

            if(userEntityAssigning.contains(userEntity)){
                if(!buildingEntities.contains(buildingEntity)) buildingEntities.add(buildingEntity);
            }
            else{
                buildingEntities.remove(buildingEntity);
            }

            userEntity.setBuildings(buildingEntities);

            userRepository.save(userEntity);
        }

        buildingEntity.setStaffs(userEntityAssigning);

        buildingRepository.save(buildingEntity);

        return "Cập nhật giao thành công!";
    }

    @Override
    public int countTotalItems() {
        return buildingRepository.countTotalItems();
    }
}
