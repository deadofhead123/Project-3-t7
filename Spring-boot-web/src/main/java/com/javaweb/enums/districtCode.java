package com.javaweb.enums;

import java.util.LinkedHashMap;
import java.util.Map;

public enum districtCode {
    QUAN_1("Quận 1"),
    QUAN_2("Quận 2"),
    QUAN_3("Quận 3"),
    QUAN_4("Quận 4"),
    QUAN_5("Quận 5"),
    QUAN_6("Quận 6"),
    QUAN_7("Quận 7"),
    QUAN_8("Quận 8");

    private final String districtName;

    districtCode(String districtName) {
        this.districtName = districtName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public static Map<String, String> type(){
        // LinkedHashMap sẽ giữ đúng thứ tự các phần tử của enum
        Map<String, String> listCode = new LinkedHashMap<String, String>();

        for(districtCode item : districtCode.values()){
            listCode.put(item.toString(), item.getDistrictName());
        }

        return listCode;
    }
}
