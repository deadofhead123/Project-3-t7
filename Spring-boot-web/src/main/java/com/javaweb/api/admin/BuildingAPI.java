package com.javaweb.api.admin;

import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/buildings")
public class BuildingAPI {
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    private Object addOrUpdateBuilding(@RequestBody BuildingDTO buildingDTO){
        return new String("Được dồi");
    }

    @GetMapping("/{id}")
    private Object loadStaffs(@PathVariable int id){
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

    @DeleteMapping("/{Ids}")
    private Object deleteBuilding(@PathVariable String[] Ids){
        return new String("Đã xóa");
    }
}
