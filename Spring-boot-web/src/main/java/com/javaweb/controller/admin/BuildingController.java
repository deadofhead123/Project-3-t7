package com.javaweb.controller.admin;

import com.javaweb.enums.buildingRentType;
import com.javaweb.enums.districtCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.service.IBuildingService;
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

    @Autowired
    private IBuildingService buildingService;

    // - Tìm kiếm tòa nhà
    //   + Khi ấn nút "Tìm kiếm" thì các field sẽ được submit lên, và cái params được điền các field đó
    //   (Do các field map trực tiếp với các field, xem trong file "list.jsp")
    @GetMapping(value = "/admin/building-list") // ModelAndView ko cho truy xuất quá 3 bậc
    private ModelAndView buildingList(@ModelAttribute(name = "modelSearch") BuildingSearchRequest params) {
        ModelAndView modelAndView = new ModelAndView("admin/building/list");

        // Tham số thứ nhất là cái item trong file html, tham số thứ 2 là model định trả ra
        modelAndView.addObject("district", districtCode.type()); // "QUAN_1", "Quận 1"
        modelAndView.addObject("rentType", buildingRentType.type()); // "NGUYEN_CAN", "Nguyên căn"
        modelAndView.addObject("staffs", userService.allStaff());

        // Kết quả tìm kiếm
        modelAndView.addObject("listBuilding", buildingService.findAll(params));

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
