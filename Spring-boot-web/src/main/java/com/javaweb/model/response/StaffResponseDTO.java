package com.javaweb.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StaffResponseDTO {
    private String userName;
    private Long staffId;
    private String checked; // Nhân viên có được giao quản lý tòa nhà không
}
