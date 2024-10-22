package com.javaweb.api.admin;

import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.impl.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/buildings")
public class BuildingAPI {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BuildingService buildingService;

    @PostMapping
    private ResponseEntity<?> addOrUpdateBuilding(@Valid @RequestBody BuildingDTO buildingDTO, BindingResult bindingResult) {    // khai báo BindingResult để hứng các lỗi
        try {
            // Kiểm tra, nếu ko có lỗi gì về field thì tiếp tục làm việc
            if (bindingResult.hasErrors()) {
                List<String> errors = bindingResult.getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());

                ResponseDTO responseDTO = new ResponseDTO();
                responseDTO.setMessage("Failed");
                responseDTO.setDetails(errors);

                return ResponseEntity.badRequest().body(responseDTO);
            }

            return ResponseEntity.ok().body("oke");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    private Object loadStaffs(@PathVariable int id) {
        // Lấy ra các nhân viên có role là staff
        List<UserEntity> userEntities = userRepository.findByStatusAndRoles_Code(1, "STAFF");

        // Lấy ra tòa nhà có id được gửi xuống


        // Lọc các nhân viên đang quản lý tòa nhà
        List<UserEntity> assignedStaffs = new ArrayList<>(); // Lấy trong bảng assignmentbuilding

        List<StaffResponseDTO> staffResponseDTOs = new ArrayList<>();

        StaffResponseDTO staff1 = new StaffResponseDTO();
        staff1.setUserName("A");
        staff1.setStaffId(1L);
        staff1.setChecked("checked");

        StaffResponseDTO staff2 = new StaffResponseDTO();
        staff2.setUserName("B");
        staff2.setStaffId(2L);
        staff2.setChecked("checked");

        StaffResponseDTO staff3 = new StaffResponseDTO();
        staff3.setUserName("C");
        staff3.setStaffId(3L);
        staff3.setChecked("unchecked");

        StaffResponseDTO staff4 = new StaffResponseDTO();
        staff4.setUserName("D");
        staff4.setStaffId(4L);
        staff4.setChecked("unchecked");

        staffResponseDTOs.add(staff1);
        staffResponseDTOs.add(staff2);
        staffResponseDTOs.add(staff3);
        staffResponseDTOs.add(staff4);

//        for(UserEntity userEntity : userEntities){
//            StaffResponseDTO staffResponseDTO = new StaffResponseDTO();
//
//            // Nếu nhân viên này đang quản lý 1 tòa nhà nào đó, thì set checked = "checked" và ngược lại
//            if(assignedStaffs.contains(userEntity)){
//                staffResponseDTO.setUserName(userEntity.getUserName());
//                staffResponseDTO.setStaffId(userEntity.getId());
//                staffResponseDTO.setChecked("checked");
//            }
//            else{
//                staffResponseDTO.setChecked("unchecked");
//            }
//
//            staffResponseDTOs.add(staffResponseDTO);
//        }

        ResponseDTO responseDTO = new ResponseDTO();

        responseDTO.setData(staffResponseDTOs);
        responseDTO.setMessage("success");

        return responseDTO;
    }

    @PutMapping("/staffs")
    private Object updateAssignmentBuilding(@RequestBody AssignmentBuildingDTO assignmentBuildingDTO) {
        // Theo mô hình 3-layer để xử lí tiếp
        return new String("Test phần giao!");
    }

    @DeleteMapping("/{Ids}")
    private Object deleteBuilding(@PathVariable String[] Ids) {
        // Theo mô hình 3-layer để xử lí tiếp
        return new String("Đã xóa");
    }
}
