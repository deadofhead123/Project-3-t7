package com.javaweb.api.admin;

import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.impl.BuildingService;
import com.javaweb.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/buildings")
public class BuildingAPI {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BuildingService buildingService;
    private BuildingRepository buildingRepository;
    @Autowired
    private UserService userService;

    // Thêm tòa nhà
    @PostMapping
    private ResponseEntity<?> addOrUpdateBuilding(@Valid @RequestBody BuildingDTO buildingDTO, BindingResult bindingResult) {    // khai báo BindingResult để hứng các lỗi
        ResponseDTO responseDTO = new ResponseDTO();

        try {
            // Kiểm tra, nếu ko có lỗi gì về field thì tiếp tục làm việc
            if (bindingResult.hasErrors()) {
                List<String> errors = bindingResult.getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());

                responseDTO.setMessage("Failed");
                responseDTO.setDetails(errors);

                return ResponseEntity.badRequest().body(responseDTO);
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

        responseDTO.setMessage(buildingService.addOrUpdateBuilding(buildingDTO));

        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> loadStaffs(@PathVariable(name = "id") Long buildingId) {
        List<StaffResponseDTO> staffResponseDTOs = userService.loadStaffs(buildingId);

        ResponseDTO responseDTO = new ResponseDTO();

        responseDTO.setData(staffResponseDTOs);
        responseDTO.setMessage("success");

        return ResponseEntity.ok().body(responseDTO);
    }

    @PutMapping("/staffs")
    private Object updateAssignmentBuilding(@RequestBody AssignmentBuildingDTO assignmentBuildingDTO) {
        // Theo mô hình 3-layer để xử lí tiếp
        return new String("Test phần giao!");
    }

    @DeleteMapping("/{ids}")
    private ResponseEntity<?> deleteBuilding(@PathVariable Long[] ids) {
        ResponseDTO responseDTO = new ResponseDTO();

        // Theo mô hình 3-layer để xử lí tiếp
        try {
            responseDTO.setMessage(buildingService.deleteBuilding(ids));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

        return ResponseEntity.ok().body(responseDTO);
    }
}
