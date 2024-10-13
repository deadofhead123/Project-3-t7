package com.javaweb.enums;

import java.util.LinkedHashMap;
import java.util.Map;

public enum buildingRentType {
    TANG_TRET("Tầng trệt"),
    NGUYEN_CAN("Nguyên căn"),
    NOI_THAT("Nội thất");

    private final String typeCode;

    buildingRentType(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public static Map<String, String> type(){
        // LinkedHashMap sẽ giữ đúng thứ tự các phần tử của enum
        Map<String, String> listTypeCode = new LinkedHashMap<String, String>();

        for(buildingRentType item : buildingRentType.values()){
            listTypeCode.put(item.toString(), item.getTypeCode());
        }

        return listTypeCode;
    }
}
