package org.example.event.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "su_kien")
public class SuKien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma_diem_danh", unique = true, nullable = false)
    private String maDiemDanh;

}


