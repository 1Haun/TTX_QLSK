package org.example.event.repository;

import org.example.event.entity.BoMon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BoMonRepository extends JpaRepository<BoMon, Long> {

    @Query("SELECT b FROM BoMon b WHERE LOWER(b.code) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(b.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<BoMon> findBoMonByCodeOrName(@Param("keyword") String keyword, Pageable pageable);

    boolean existsByCode(String code);

    Optional<BoMon> findByCode(String code);

}
