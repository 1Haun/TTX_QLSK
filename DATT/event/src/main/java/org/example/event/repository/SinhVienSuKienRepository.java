package org.example.event.repository;

import org.example.event.entity.SinhVienSuKien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SinhVienSuKienRepository extends JpaRepository<SinhVienSuKien, Long> {
    boolean existsBySinhVienIdAndSuKienId(Long sinhVienId, Long suKienId);
    SinhVienSuKien findBySinhVienIdAndSuKienId(Long sinhVienId, Long suKienId);
}
