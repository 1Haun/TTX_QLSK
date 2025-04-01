package org.example.event.repository;

import org.example.event.entity.KeHoachSuKien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface KeHoachSuKienRepository extends JpaRepository<KeHoachSuKien, Long> {
    List<KeHoachSuKien> findByTrangThai(String trangThai);

    @Query("SELECT k FROM KeHoachSuKien k WHERE " +
            "(COALESCE(:tenKeHoach, '') = '' OR LOWER(k.tenKeHoach) LIKE LOWER(CONCAT('%', :tenKeHoach, '%'))) " +
            "AND (COALESCE(:hocKy, '') = '' OR k.hocKy = :hocKy) " +
            "AND (COALESCE(:trangThai, '') = '' OR k.trangThai = :trangThai) " +
            "AND (COALESCE(:boMon, '') = '' OR k.boMon = :boMon)")
    Page<KeHoachSuKien> searchAndFilter(
            @Param("tenKeHoach") String tenKeHoach,
            @Param("hocKy") String hocKy,
            @Param("trangThai") String trangThai,
            @Param("boMon") String boMon,
            Pageable pageable);
    boolean existsByTenKeHoach(String tenKeHoach);

}
