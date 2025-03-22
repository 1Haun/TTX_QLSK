package org.example.event.repository;

import org.example.event.entity.SuKien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuKienRepository extends JpaRepository<SuKien, Long> {
    SuKien findByMaDiemDanh(String maDiemDanh);
}
