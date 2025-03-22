package org.example.event.repository;

import org.example.event.entity.SinhVien;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SinhVienRepository extends JpaRepository<SinhVien, Long> {
    @Query("SELECT DISTINCT s FROM SinhVien s WHERE LOWER(s.tenSinhVien) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(s.hocKy) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<SinhVien> findSinhVienByNameOrObject(@Param("keyword") String keyword, Pageable pageable);

}
