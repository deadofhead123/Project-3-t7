package com.javaweb.controller.admin;

import com.javaweb.constant.SystemConstant;
import com.javaweb.enums.buildingRentType;
import com.javaweb.enums.districtCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.service.IBuildingService;
import com.javaweb.service.IUserService;
import com.javaweb.utils.DisplayTagUtils;
import com.javaweb.utils.MessageUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController(value = "buildingControllerOfAdmin")
public class BuildingController {
    @Autowired
    private IUserService userService;

    @Autowired
    private IBuildingService buildingService;

    @Autowired
    private MessageUtils messageUtil;

    // - Tìm kiếm tòa nhà
    //   + Khi ấn nút "Tìm kiếm" thì các field sẽ được submit lên, và cái params được điền các field đó
    //   (Do các field map trực tiếp với các field, xem trong file "list.jsp")
    @GetMapping(value = "/admin/building-list") // ModelAndView ko cho truy xuất quá 3 bậc
    private ModelAndView buildingList(@ModelAttribute(name = "modelSearch") BuildingSearchRequest params, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("admin/building/list");

        // Tham số thứ nhất là cái item trong file html, tham số thứ 2 là model định trả ra
        modelAndView.addObject("district", districtCode.type()); // "QUAN_1", "Quận 1"
        modelAndView.addObject("rentType", buildingRentType.type()); // "NGUYEN_CAN", "Nguyên căn"
        modelAndView.addObject("staffs", userService.allStaff());

        DisplayTagUtils.of(request, params);

        List<BuildingSearchResponse> buildingSearchResponses =  buildingService.findAll(params, PageRequest.of(params.getPage() - 1, params.getMaxPageItems()));

        BuildingSearchResponse buildingSearchResult = new BuildingSearchResponse();

        buildingSearchResult.setListResult(buildingSearchResponses);
        buildingSearchResult.setTotalItems(buildingService.countTotalItems());

        // Kết quả tìm kiếm
        modelAndView.addObject("buildingSearchResult", buildingSearchResult);
        initMessageResponse(modelAndView, request);

        return modelAndView;
    }

    // Hiển thị màn hình Thêm mới
    @GetMapping(value = "/admin/building-edit")
    private ModelAndView buildingEdit(@ModelAttribute(name = "buildingEdit") BuildingDTO params) {
        ModelAndView modelAndView = new ModelAndView("admin/building/edit");

        modelAndView.addObject("district", districtCode.type()); // "QUAN_1", "Quận 1"
        modelAndView.addObject("rentType", buildingRentType.type()); // "NGUYEN_CAN", "Nguyên căn"

        return modelAndView;
    }

    // Hiển thị màn hình Cập nhật
    @GetMapping(value = "/admin/building-edit-{id}")
    private ModelAndView buildingEdit(@ModelAttribute(name = "buildingEdit") BuildingDTO buildingDTO, @PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("admin/building/edit");

        modelAndView.addObject("district", districtCode.type()); // "QUAN_1", "Quận 1"
        modelAndView.addObject("rentType", buildingRentType.type()); // "NGUYEN_CAN", "Nguyên căn"
        modelAndView.addObject("buildingEdit", buildingService.findOneBuildingById(id));

        return modelAndView;
    }

    private void initMessageResponse(ModelAndView mav, HttpServletRequest request) {
        String message = request.getParameter("message");

        if (message != null && StringUtils.isNotEmpty(message)) {
            Map<String, String> messageMap = messageUtil.getMessage(message);
            mav.addObject(SystemConstant.ALERT, messageMap.get(SystemConstant.ALERT));
            mav.addObject(SystemConstant.MESSAGE_RESPONSE, messageMap.get(SystemConstant.MESSAGE_RESPONSE));
        }
    }
}
