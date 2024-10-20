package com.javaweb.model.response;

import com.javaweb.model.dto.AbstractDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuildingSearchResponse extends AbstractDTO {
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
