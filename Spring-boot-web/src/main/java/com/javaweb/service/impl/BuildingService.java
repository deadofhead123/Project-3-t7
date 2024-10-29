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
import com.javaweb.utils.UploadFileUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
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

    @Autowired
    private UploadFileUtils uploadFileUtils;

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

//    public BuildingDTO save(BuildingDTO buildingDTO) {
//        Long buildingId = buildingDTO.getId();
//
//        BuildingEntity buildingEntity = buildingConverter.convertToEntity(buildingDTO);
//
//        if (buildingId != null) { // update
//            BuildingEntity foundBuilding = buildingRepository.findById(buildingId)
//                    .orElseThrow(() -> new NotFoundException("Building not found!"));
//            buildingEntity.setImage(foundBuilding.getImage());
//        }
//        saveThumbnail(buildingDTO, buildingEntity);
//
//        return buildingConverter.convertToDto(buildingRepository.save(buildingEntity));
//    }

    @Override
    public String addOrUpdateBuilding(BuildingDTO building) {
        BuildingEntity editBuilding = new BuildingEntity();
        String result = "";

        // Thêm tòa nhà thì ko có id, còn cập nhật thì có
        if (building.getId() == null) {
            result = "Thêm mới thành công!";
        } else {
            editBuilding = buildingRepository.getOne(building.getId());

            // Xóa các rentArea cũ để tránh bị trùng
            rentAreaRepository.deleteAllByBuildingEntity(editBuilding);

            result = "Cập nhật thành công!";
        }

        // Gắn dữ liệu sang Entity
        editBuilding = modelMapper.map(building, BuildingEntity.class);

        // Lưu ảnh vào máy (phải lưu sau vì lúc này buildingDTO mới có dữ liệu ảnh :)) )
        saveThumbnail(building, editBuilding);

        // Gắn Type
        editBuilding.setType(String.join(",", building.getTypeCode()));

        // Lưu xuống CSDL
        buildingRepository.save(editBuilding);

        // Lưu các rentArea của tòa nhà
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
        buildingRepository.deleteByIdIn(listId);

        return "Xóa thành công!";
    }

    @Override
    public String updateAssignmentBuilding(AssignmentBuildingDTO assignmentBuildingDTO) {
        BuildingEntity buildingEntity = buildingRepository.getOne(assignmentBuildingDTO.getBuildingId());

        // Tìm ra các staff được giao quản lý tòa nhà này
        List<UserEntity> userEntityAssigning = userRepository.findByIdIn(assignmentBuildingDTO.getStaffIds());

        // Giao
        buildingEntity.setStaffs(userEntityAssigning);

        // Cập nhật tòa nhà vào CSDL
        buildingRepository.save(buildingEntity);

        return "Cập nhật giao thành công!";
    }

    // Lưu ảnh của thêm mới hoặc sửa tòa nhà
    private void saveThumbnail(BuildingDTO buildingDTO, BuildingEntity buildingEntity) {
        String path = "/building/" + buildingDTO.getImageName();

        if (null != buildingDTO.getImageBase64()) {
            if (null != buildingEntity.getImage()) {
                if (!path.equals(buildingEntity.getImage())) {
                    File file = new File("C://home/office" + buildingEntity.getImage());
                    file.delete();
                }
            }

            byte[] bytes = Base64.decodeBase64(buildingDTO.getImageBase64().getBytes());

            uploadFileUtils.writeOrUpdate(path, bytes);
            buildingEntity.setImage(path);
        }
    }

    @Override
    public int countTotalItems() {
        return buildingRepository.countTotalItems();
    }
}
