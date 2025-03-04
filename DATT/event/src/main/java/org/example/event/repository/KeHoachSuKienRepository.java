package org.example.event.repository;

import org.example.event.entity.KeHoachSuKien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface KeHoachSuKienRepository extends JpaRepository<KeHoachSuKien, Long> {

    @Query("SELECT k FROM KeHoachSuKien k WHERE " +
            "(:tenKeHoach IS NULL OR LOWER(k.tenKeHoach) LIKE LOWER(CONCAT('%', :tenKeHoach, '%'))) " +
            "AND (:hocKy IS NULL OR k.hocKy = :hocKy) " +
            "AND (:trangThai IS NULL OR k.trangThai = :trangThai) " +
            "AND (:boMon IS NULL OR k.boMon = :boMon)")
    Page<KeHoachSuKien> searchAndFilter(
            @Param("tenKeHoach") String tenKeHoach,
            @Param("hocKy") String hocKy,
            @Param("trangThai") String trangThai,
            @Param("boMon") String boMon,
            Pageable pageable);
    boolean existsByTenKeHoach(String tenKeHoach);

}
