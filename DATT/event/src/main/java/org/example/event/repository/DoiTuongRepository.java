package org.example.event.repository;
import org.example.event.entity.DoiTuong;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface DoiTuongRepository extends JpaRepository<DoiTuong, Long> {
    @Query("SELECT DISTINCT dt FROM DoiTuong  dt WHERE LOWER(dt.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<DoiTuong> findDoiTuongByCodeOrName(@Param("keyword") String keyword, Pageable pageable);
}
