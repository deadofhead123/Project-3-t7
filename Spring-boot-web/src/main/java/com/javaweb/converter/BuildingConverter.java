package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.enums.districtCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.BuildingSearchResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

// Để khi gọi class này, mà trong đó nó gọi đến các interface khác, thì ta phải thêm Annotation @Component
@Component
public class BuildingConverter {
    @Autowired
    private ModelMapper modelMapper;

    public BuildingSearchResponse convertToSearchResponseDTO(BuildingEntity ele) { // ele: element
        // Lấy danh sách kết quả
        // Sử dụng Model Mapper để copy các field giống nhau trong BuildingEntity sang BuildingResponseDTO
        BuildingSearchResponse new_building = modelMapper.map(ele, BuildingSearchResponse.class);

        new_building.setAddress(ele.getStreet() + ", " + ele.getWard() + ", " + districtCode.type().get(ele.getDistrict()) );

        // Xem class RentAreaEntity và RentAreaRepositoryImpl để biết thêm
        new_building.setRentArea( ele.getRentAreaEntities().stream().
                map(x -> x.getValue().toString() ).collect(Collectors.joining(", ")) );

        return new_building;
    }

    public BuildingDTO convertToDTO(BuildingEntity ele) { // ele: element
        // Lấy danh sách kết quả
        // Sử dụng Model Mapper để copy các field giống nhau trong BuildingEntity sang BuildingResponseDTO
        BuildingDTO new_building = modelMapper.map(ele, BuildingDTO.class);

        new_building.setImage(ele.getImage());

        new_building.setTypeCode(Arrays.asList(ele.getType().split(",")));

        // Xem class RentAreaEntity và RentAreaRepositoryImpl để biết thêm
        new_building.setRentArea( ele.getRentAreaEntities().stream().
                map(x -> x.getValue().toString() ).collect(Collectors.joining(",")) );

        return new_building;
    }
}
