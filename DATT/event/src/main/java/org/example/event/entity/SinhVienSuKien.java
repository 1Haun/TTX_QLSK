package org.example.event.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sinh_vien_su_kien")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SinhVienSuKien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST) // Tự động lưu sinh viên nếu chưa có
    @JoinColumn(name = "sinh_vien_id", nullable = false)
    private SinhVien sinhVien;

    @ManyToOne
    @JoinColumn(name = "ke_hoach_su_kien_id", nullable = false)
    private KeHoachSuKien keHoachSuKien;
}
