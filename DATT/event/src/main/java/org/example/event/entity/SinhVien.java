package org.example.event.entity;

<<<<<<< HEAD

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sinh_vien")
public class SinhVien {

=======
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "sinhvien")
public class SinhVien {
>>>>>>> origin/main
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

<<<<<<< HEAD
    private String maSinhVien;
    private String hoTen;
    private String email;
    private String soDienThoai;

    @OneToMany(mappedBy = "sinhVien", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SinhVienSuKien> danhSachSuKien;
}

=======
    @Column(name = "ten_sinh_vien", nullable = false, unique = true, length = 255)
    private String tenSinhVien;

    @Column(name = "hoc_ky", nullable = false, length = 255)
    private String hocKy;

    @Column(name = "chuyen_nganh", nullable = false, length = 255)
    private String chuyenNganh;

    @Column(name = "bo_mon", nullable = false, length = 255)
    private String boMon;

}
>>>>>>> origin/main
