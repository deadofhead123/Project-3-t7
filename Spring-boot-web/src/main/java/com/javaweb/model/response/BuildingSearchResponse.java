package com.javaweb.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuildingSearchResponse {
    private String name;
    private String address;
    private Integer numberOfBasement;
    private String managerName;
    private String managerPhoneNumber;
    private Integer floorArea;
    private Integer emptyArea;
    private String rentArea;
    private Integer rentPrice;
    private Integer serviceFee;
    private Integer brokerageFee;
}
