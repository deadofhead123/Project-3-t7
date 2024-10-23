package com.javaweb.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class BuildingDTO extends AbstractDTO{
    @NotBlank(message = "Tên tòa nhà không được để trống!") // Liên quan đến phần Validate
    private String name;

    @NotBlank(message = "Tên đường không được để trống!")
    private String street;

    @NotBlank(message = "Tên phường không được để trống!")
    private String ward;

    @NotBlank(message = "Tên quận không được để trống!")
    private String district;

    @Min(value = 1, message = "Số tầng hầm phải là số dương!")
    private Long numberOfBasement;

    @NotNull(message = "Diện tích sàn không được để trống!")
    @Min(value = 1, message = "Diện tích sàn phải là số dương!")
    private Long floorArea;

    private String level;

//    @NotNull(message = "typeCode không được rỗng!")
    @Size(min = 1, message = "Phải có ít nhất 1 typeCode")
    private List<String> typeCode;

    private String overtimeFee;
    private String electricityFee;
    private String deposit;
    private String payment;
    private String rentTime;
    private String decorationTime;

    @NotNull(message = "Giá thuê không được để trống!")
    @Min(value = 1, message = "Giá thuê phải là số dương")
    private Long rentPrice;

    private String rentPriceDescription;
    private String carFee;
    private String motorFee;
    private String structure;
    private String direction;
    private String note;

    @NotBlank(message = "Diện tích thuê không được để trống!")
    private String rentArea;

    private String managerName;
    private String managerPhoneNumber;
    private String serviceFee;

    @Min(value = 1, message = "Phí môi giới phải là số dương!")
    private double brokerageFee;
    private String image;
    private String imageBase64;
    private String imageName;
    private String waterFee;
    private String map;

    private Map<String, String> buildingDTOs = new HashMap<>();
}
