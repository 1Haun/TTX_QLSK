package org.example.event.repository;

import org.example.event.entity.ChuyenNganh;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ChuyenNganhRepository extends JpaRepository<ChuyenNganh, Long> {

    @Query("SELECT c FROM ChuyenNganh c WHERE " +
            "(:maChuyenNganh IS NULL OR c.maChuyenNganh LIKE %:maChuyenNganh%) AND " +
            "(:tenChuyenNganh IS NULL OR c.tenChuyenNganh LIKE %:tenChuyenNganh%)")
    Page<ChuyenNganh> searchChuyenNganh(@Param("maChuyenNganh") String maChuyenNganh,
                                        @Param("tenChuyenNganh") String tenChuyenNganh,
                                        Pageable pageable);
}
