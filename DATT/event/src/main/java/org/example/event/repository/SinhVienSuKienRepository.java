package org.example.event.repository;

import org.example.event.entity.SinhVienSuKien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SinhVienSuKienRepository extends JpaRepository<SinhVienSuKien, Long> {

    List<SinhVienSuKien> findBySinhVien_Id(Long sinhVienId);
}
