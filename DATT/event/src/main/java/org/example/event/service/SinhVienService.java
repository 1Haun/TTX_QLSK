package org.example.event.service;

import org.example.event.entity.SinhVien;
import org.example.event.repository.SinhVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class SinhVienService {

    @Autowired
    private SinhVienRepository sinhVienRepository;

    public SinhVien findOrCreateSinhVien(SinhVien sinhVien) {
        Optional<SinhVien> existingSinhVien = sinhVienRepository.findByMaSinhVien(sinhVien.getMaSinhVien());
        return existingSinhVien.orElseGet(() -> sinhVienRepository.save(sinhVien));
    }
}
