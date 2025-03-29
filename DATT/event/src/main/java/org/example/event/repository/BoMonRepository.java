package org.example.event.repository;


import org.example.event.entity.BoMon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoMonRepository extends JpaRepository<BoMon, Long> {
//    Page<BoMon> findBoMonByCodeOrName(String code, String name, Pageable pageable);
    @Query("SELECT DISTINCT b FROM BoMon b WHERE LOWER(b.code) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(b.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<BoMon> findBoMonByCodeOrName(@Param("keyword") String keyword, Pageable pageable);


}
