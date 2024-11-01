package com.javaweb.controller.admin;

import com.javaweb.enums.buildingRentType;
import com.javaweb.enums.districtCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController(value = "buildingControllerOfAdmin")
public class BuildingController {
    @Autowired
    private IUserService userService;

    @GetMapping(value = "/admin/building-list") // ModelAndView ko cho truy xuất quá 3 bậc
    private ModelAndView buildingList(@ModelAttribute(name = "modelSearch") BuildingSearchRequest params) {
        ModelAndView modelAndView = new ModelAndView("admin/building/list");

        // Tham số thứ nhất là cái item trong file html, tham số thứ 2 là model định trả ra
        modelAndView.addObject("district", districtCode.type()); // "QUAN_1", "Quận 1"
        modelAndView.addObject("rentType", buildingRentType.type()); // "NGUYEN_CAN", "Nguyên căn"
        modelAndView.addObject("staffs", userService.allStaff());

        List<BuildingSearchResponse> responses = new ArrayList<BuildingSearchResponse>();

        BuildingSearchResponse building1 = new BuildingSearchResponse();

        building1.setId(2L);
        building1.setName("ABC 1");
        building1.setAddress("Đường 1, Quận 2");
        building1.setNumberOfBasement(2);
        building1.setManagerName("Bette Porter");
        building1.setManagerPhoneNumber("12345678");
        building1.setFloorArea(200);
        building1.setEmptyArea(234);
        building1.setRentArea("100, 200, 400");
        building1.setRentPrice(18);

        BuildingSearchResponse building2 = new BuildingSearchResponse();

        building2.setId(3L);
        building2.setName("ABC 2");
        building2.setAddress("Đường 1, Quận 2");
        building2.setNumberOfBasement(2);
        building2.setManagerName("Tina Kennard");
        building2.setManagerPhoneNumber("12345678");
        building2.setFloorArea(200);
        building2.setEmptyArea(234);
        building2.setRentArea("100, 200, 400");
        building2.setRentPrice(18);

        responses.add(building1);
        responses.add(building2);

        for(Long i = 4L ; i <= 15L ; i++){
            BuildingSearchResponse newBuilding = new BuildingSearchResponse();

            newBuilding.setId(i);
            newBuilding.setName("ABC " + Long.toString(i));
            newBuilding.setAddress("Đường 1, Quận 2");
            newBuilding.setNumberOfBasement(2);
            newBuilding.setManagerName("Dana Fairbanks");
            newBuilding.setManagerPhoneNumber("12345678");
            newBuilding.setFloorArea(200);
            newBuilding.setEmptyArea(234);
            newBuilding.setRentArea("100, 200, 400");
            newBuilding.setRentPrice(18);

            responses.add(newBuilding);
        }

        modelAndView.addObject("listBuilding", responses);

        return modelAndView;
    }

    @GetMapping(value = "/admin/building-edit")
    private ModelAndView buildingEdit(@ModelAttribute(name = "buildingEdit") BuildingDTO params) {
        ModelAndView modelAndView = new ModelAndView("admin/building/edit");

        modelAndView.addObject("district", districtCode.type()); // "QUAN_1", "Quận 1"
        modelAndView.addObject("rentType", buildingRentType.type()); // "NGUYEN_CAN", "Nguyên căn"

        return modelAndView;
    }

    @GetMapping(value = "/admin/building-edit-{id}")
    private ModelAndView buildingEdit(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("admin/building/edit");

        modelAndView.addObject("district", districtCode.type()); // "QUAN_1", "Quận 1"
        modelAndView.addObject("rentType", buildingRentType.type()); // "NGUYEN_CAN", "Nguyên căn"

        BuildingDTO buildingDTO = new BuildingDTO();

        buildingDTO.setId(id);
        buildingDTO.setName("ABC 1");
        buildingDTO.setStreet("Trần Phú Street");
        buildingDTO.setLevel("Thấp");

        List<String> tC = new ArrayList<>();
        tC.add("NOI_THAT");
        tC.add("NGUYEN_CAN");

        buildingDTO.setTypeCode(tC);

        modelAndView.addObject("buildingEdit", buildingDTO);

        return modelAndView;
    }
}
