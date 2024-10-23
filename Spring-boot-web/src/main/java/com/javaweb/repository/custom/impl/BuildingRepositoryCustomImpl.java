package com.javaweb.repository.custom.impl;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.List;

@Repository
public class BuildingRepositoryCustomImpl implements BuildingRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    // Xử lí join bảng
    public void queryJoin(BuildingSearchRequest buildingSearchRequest, StringBuilder join) {

        // Join bảng assignmentbuilding (Phải kết nối với bảng AssignmentBuilding để
        // kiểm tra staffId)
        Integer staffId = buildingSearchRequest.getStaffId();
        if (staffId != null)
            join.append(" INNER JOIN assignmentbuilding asmbld ON b.id = asmbld.buildingid");
    }

    // - Xử lí trường hợp Normal của where
    // + Normal: like(data là string); =(data dạng số); field ko liên quan bảng khác; ko có >=, <=
    public void queryWhereNormal(BuildingSearchRequest buildingSearchRequest, StringBuilder where) {

//		for (Map.Entry<String, String> ite : buildingSearchBuilder.entrySet()) {
//			String key = ite.getKey();
//
//			if (!key.equals("staffId") && !key.equals("typeCode") && !key.startsWith("rentArea")
//					&& !key.startsWith("rentPrice")) // Vì rentArea có rentAreaFrom, rentAreaTo nên dùng startsWith để
//														// check được cả 2, tương tự với rentPrice
//			{
//				String value = ite.getValue();
//				// Xử lí data dạng số
//				// * Vì khi convert các key dạng String qua số, có thể sẽ lỗi (VD: key direction
//				// có dạng String)
//				if (NumberUtil.checkNumber(value)) {
//					where.append(" AND b." + key + " = " + value);
//				} else { // Xử lí data dạng xâu
//					where.append(" AND b." + key + " LIKE '%" + value + "%'");
//				}
//			}
//		}
        // Xem thêm về Java Reflection
        try {
            Field[] fields = BuildingSearchRequest.class.getDeclaredFields();

            for(Field it : fields) {
                it.setAccessible(true);

                String key = it.getName();

                if (!key.equals("staffId") && !key.equals("typeCode") && !key.startsWith("rentArea") && !key.startsWith("rentPrice") ) {
                    Object value = it.get(buildingSearchRequest);
                    // Xử lí data dạng số
                    // * Vì khi convert các key dạng String qua số, có thể sẽ lỗi (VD: key direction
                    // có dạng String)
                    if( value != null && !value.equals("")) {
                        if( it.getType().getName().equals("java.lang.Long") || it.getType().getName().equals("java.lang.Integer") ) {
                            where.append(" AND b." + key.toLowerCase() + " = " + value);
                        }
                        else {
                            where.append(" AND b." + key.toLowerCase() + " LIKE '%" + value + "%'");
                        }
                    }
                }
            }
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    // - Xử lí trường hợp Special của where
    // + Where: có >=, <= ...
    // * Nếu số field cần xử lí ít thì có thể lấy luôn ra cũng được
    public void queryWhereSpecial(BuildingSearchRequest buildingSearchRequest, StringBuilder where) {
        // StaffId
        Integer staffId = buildingSearchRequest.getStaffId();
        if (staffId != null)
            where.append(" AND asmbld.staffid = " + staffId);

        // rentAreaFrom, rentAreaTo
        Integer rentAreaFrom = buildingSearchRequest.getRentAreaFrom();
        Integer rentAreaTo = buildingSearchRequest.getRentAreaTo();
        if(rentAreaFrom != null || rentAreaTo != null) {
            where.append(" AND EXISTS (SELECT * FROM rentarea ra WHERE ra.buildingid = b.id");

            if (rentAreaFrom != null)
                where.append(" AND ra.value >= " + rentAreaFrom);

            if (rentAreaTo != null)
                where.append(" AND ra.value <= " + rentAreaTo);

            where.append(")");
        }

        // rentPriceFrom, rentPriceTo
        Integer rentPriceFrom = buildingSearchRequest.getRentPriceFrom();
        if (rentPriceFrom != null)
            where.append(" AND b.rentprice >= " + rentPriceFrom);

        Integer rentPriceTo = buildingSearchRequest.getRentPriceTo();
        if (rentPriceTo != null)
            where.append(" AND b.rentprice <= " + rentPriceTo);

        // TypeCode, xây dựng theo kiểu của Java 7
//		if (params.containsKey("typeCode") && typeCodeList.size() > 0) {
//			String tC = "";
//
//			for (int i = 0; i < typeCodeList.size(); i++) {
//				if (typeCodeList.get(i) != null)
//					tC += "'" + typeCodeList.get(i) + "'";
//
//				if (i != typeCodeList.size() - 1)
//					tC += ",";
//			}
//
//			where.append(" AND rt.code IN(" + tC + ")");
//		}

        List<String> typeCode = buildingSearchRequest.getTypeCode();
        // TypeCode, xây dựng theo kiểu của Java 8
        if (typeCode != null && typeCode.size() != 0) {
            where.append(" AND b.type LIKE '%" + String.join(",", typeCode) + "%'");
        }
    }

    // ---------------------------------------------------------------------- Hàm xử lí chính
    @Override
    public List<BuildingEntity> findAll(BuildingSearchRequest buildingSearchRequest) {
        // Câu lệnh sql
        StringBuilder sql = new StringBuilder("SELECT b.* FROM building b");
        StringBuilder join = new StringBuilder("");
        StringBuilder where = new StringBuilder(" WHERE 1=1");

        // Xây dựng các thành phần JOIN, WHERE
        queryJoin(buildingSearchRequest, join);
        queryWhereNormal(buildingSearchRequest, where);
        queryWhereSpecial(buildingSearchRequest, where);

        // Kết hợp các thành phần của câu query lại
        sql.append(join).append(where).append(" GROUP BY b.id");

        // In ra câu query để check
        System.out.println(sql);

        // Kết nối với database để lấy dữ liệu
        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);

        // Trả về các kết quả tìm được
        return query.getResultList();
    }

}
