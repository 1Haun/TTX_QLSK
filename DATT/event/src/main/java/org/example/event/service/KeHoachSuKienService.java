package org.example.event.service;

import org.example.event.entity.ChuyenNganh;
import org.example.event.entity.KeHoachSuKien;
import org.example.event.repository.KeHoachSuKienRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class KeHoachSuKienService {
    private final KeHoachSuKienRepository repository;

    public KeHoachSuKienService(KeHoachSuKienRepository repository) {
        this.repository = repository;
    }

    public Page<KeHoachSuKien> searchKeHoach(String tenKeHoach, String hocKy, String trangThai, String boMon, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        // Kiểm tra nếu tất cả tham số lọc đều null thì gọi repository.findAll(pageable)
        if ((tenKeHoach == null || tenKeHoach.trim().isEmpty()) &&
                (hocKy == null || hocKy.trim().isEmpty()) &&
                (trangThai == null || trangThai.trim().isEmpty()) &&
                (boMon == null || boMon.trim().isEmpty())) {
            return repository.findAll(pageable);
        }

        return repository.searchAndFilter(tenKeHoach, hocKy, trangThai, boMon, pageable);
    }
    public Optional<KeHoachSuKien> findById(Long id) {
        Optional<KeHoachSuKien> result = repository.findById(id);
        System.out.println("🔍 Debug findById: " + result);
        return result;
    }

}
