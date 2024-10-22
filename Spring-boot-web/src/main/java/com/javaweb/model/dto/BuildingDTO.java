package com.javaweb.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class BuildingDTO extends AbstractDTO{
    @NotBlank(message = "Tên tòa nhà không được để trống!") // Liên quan đến phần Validate
    private String name;
    private String street;
    private String ward;
    private String district;

    @Min(value = 0, message = "Số tầng hầm phải là số dương")
    private Long numberOfBasement;
    private Long floorArea;
    private String level;

//    @NotNull(message = "typeCode không được rỗng!")
//    @Size(min = 1, message = "Phải có nhiều hơn 1 typeCode")
    private List<String> typeCode;
    private String overtimeFee;
    private String electricityFee;
    private String deposit;
    private String payment;
    private String rentTime;
    private String decorationTime;
    private String rentPriceDescription;
    private String carFee;
    private String motorFee;
    private String structure;
    private String direction;
    private String note;
    private Long rentArea;
    private String managerName;
    private String managerPhoneNumber;
    private Long rentPrice;
    private String serviceFee;
    private double brokerageFee;
    private String image;
    private String imageBase64;
    private String imageName;
    private String waterFee;
    private String map;

    private Map<String, String> buildingDTOs = new HashMap<>();
}
