package org.example.event.service;

import org.example.event.entity.KeHoachSuKien;
import org.example.event.repository.KeHoachSuKienRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class KeHoachSuKienService {
    private final KeHoachSuKienRepository repository;

    public KeHoachSuKienService(KeHoachSuKienRepository repository) {
        this.repository = repository;
    }

    public Page<KeHoachSuKien> searchKeHoach(
            String tenKeHoach, String hocKy, String trangThai, String boMon, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        if (tenKeHoach != null && tenKeHoach.trim().isEmpty()) {
            tenKeHoach = null;
        }
        if (hocKy != null && hocKy.trim().isEmpty()) {
            hocKy = null;
        }
        if (trangThai != null && trangThai.trim().isEmpty()) {
            trangThai = null;
        }
        if (boMon != null && boMon.trim().isEmpty()) {
            boMon = null;
        }

        return repository.searchAndFilter(tenKeHoach, hocKy, trangThai, boMon, pageable);
    }
}
