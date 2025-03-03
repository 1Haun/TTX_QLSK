package org.example.event.service;

import org.example.event.entity.ChuyenNganh;
import org.example.event.repository.ChuyenNganhRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChuyenNganhService {

    @Autowired
    private ChuyenNganhRepository chuyenNganhRepository;

    public List<ChuyenNganh> getAllChuyenNganh() {
        return chuyenNganhRepository.findAll();
    }

    public Page<ChuyenNganh> filterChuyenNganh(String maChuyenNganh, String tenChuyenNganh, Pageable pageable) {
        return chuyenNganhRepository.searchChuyenNganh(maChuyenNganh, tenChuyenNganh, pageable);
    }

    public ChuyenNganh getChuyenNganhById(Long id) {
        return chuyenNganhRepository.findById(id).orElse(null);
    }
}
