package org.example.event.repository;

import org.example.event.entity.BoMon;
import org.example.event.entity.TheLoai;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TheLoaiRepository extends JpaRepository<TheLoai, Long> {
    @Query("SELECT DISTINCT t FROM TheLoai t WHERE LOWER(t.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<TheLoai> findTheLoaiByName(@Param("keyword") String keyword, Pageable pageable);

}
