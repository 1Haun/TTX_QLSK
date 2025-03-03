package org.example.event.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "chuyen_nganh")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChuyenNganh {
//STT,mã chuyên ngành, tên chuyên ngành)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma_chuyen_nganh", nullable = false, unique = true, length = 255)
    private String maChuyenNganh;

    @Column(name = "ten_chuyen_nganh", nullable = false, length = 255)
    private String tenChuyenNganh;

}
