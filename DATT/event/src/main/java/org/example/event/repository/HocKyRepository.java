package org.example.event.repository;

import org.example.event.entity.HocKy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HocKyRepository extends JpaRepository<HocKy, Long> {
    Page<HocKy> findAll(Pageable pageable);
}
