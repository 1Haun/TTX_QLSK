package org.example.event.repository;

import org.example.event.entity.HocKy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HocKyRepository extends JpaRepository<HocKy, Long> {

}
