package org.example.event.repository;


import org.example.event.entity.SinhVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SinhVienRepository extends JpaRepository<SinhVien, Long> {
//    SinhVien findByMaSinhVien(String maSinhVien);
    Optional<SinhVien> findByMaSinhVien(String maSinhVien);
}


