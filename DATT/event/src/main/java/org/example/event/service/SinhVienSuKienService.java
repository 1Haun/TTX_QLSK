package org.example.event.service;

import jakarta.transaction.Transactional;
import org.example.event.entity.KeHoachSuKien;
import org.example.event.entity.SinhVien;
import org.example.event.entity.SinhVienSuKien;
import org.example.event.repository.KeHoachSuKienRepository;
import org.example.event.repository.SinhVienRepository;
import org.example.event.repository.SinhVienSuKienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SinhVienSuKienService {
    @Autowired
    private SinhVienSuKienRepository sinhVienSuKienRepository;
    @Autowired
    private SinhVienRepository sinhVienRepository;

    @Autowired
    private KeHoachSuKienRepository keHoachSuKienRepository;

    public List<KeHoachSuKien> getKeHoachDaDuyet() {
        return keHoachSuKienRepository.findByTrangThai("Đã duyệt");
    }
    public List<SinhVienSuKien> getSuKienDaDangKy() {
        return sinhVienSuKienRepository.findAll();
    }

    @Transactional
    public SinhVienSuKien dangKySuKien(SinhVienSuKien sinhVienSuKien) {
        SinhVien sinhVien = sinhVienSuKien.getSinhVien();

        // Kiểm tra nếu sinhVien chưa có trong DB thì lưu trước
        if (sinhVien.getId() == null || !sinhVienRepository.existsById(sinhVien.getId())) {
            sinhVien = sinhVienRepository.save(sinhVien);
        }

        // Gán lại sinh viên đã lưu
        sinhVienSuKien.setSinhVien(sinhVien);

        // Lưu đăng ký sự kiện
        return sinhVienSuKienRepository.save(sinhVienSuKien);
    }
}

