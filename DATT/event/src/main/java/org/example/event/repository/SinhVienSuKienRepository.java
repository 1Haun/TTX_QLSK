package org.example.event.repository;

import org.example.event.entity.KeHoachSuKien;
import org.example.event.entity.SinhVienSuKien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SinhVienSuKienRepository extends JpaRepository<SinhVienSuKien, Long> {
    @Query("SELECT s.keHoachSuKien FROM SinhVienSuKien s WHERE s.sinhVien.id = :sinhVienId")
    List<KeHoachSuKien> findAllKeHoachSuKienDaDangKy(@Param("sinhVienId") Long sinhVienId);
    List<SinhVienSuKien> findBySinhVien_Id(Long sinhVienId);
}
