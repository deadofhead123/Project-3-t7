package com.javaweb.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AssignmentBuildingDTO {
    @JsonProperty("id")
    private Long buildingId;

    @JsonProperty("staffs")
    private List<Long> staffIds;
}
