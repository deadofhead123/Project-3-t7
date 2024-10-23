package com.javaweb.repository;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BuildingRepository extends JpaRepository<BuildingEntity, Long>, BuildingRepositoryCustom {
    // - Kiểu như là viết câu query bằng tên hàm, nhưng mà field sau từ "By" phải đúng với tên field trong Entity mà bạn cần truy cập
    //   + Bắt buộc phải truyền đủ, đúng thứ tự các tham số đã ghi trong tên hàm
    //	 + Nếu cần tìm theo nhiều field thì nên tự build vì lúc đó mà viết cái tên hàm thì chắc dài cả cây số:))))
    List<BuildingEntity> findByNameContaining(String name);

    // Tìm 1 tòa nhà
    BuildingEntity findOneBuildingById(long id);

    // - Tìm theo nhiều field
//    List<BuildingEntity> findByNameContainingAndWardContaining(String name, String ward);

    // - Tìm theo nhiều field (có cả field của Entity có quan hệ với Entity hiện tại
//    List<BuildingEntity> findByNameContainingAndDistrict_NameContaining(String name, String district);

    // Xóa hết các building có id trong danh sách này
    void deleteByIdIn(Integer[] ids);

    List<BuildingEntity> findByIdIn(Integer[] ids);
}
