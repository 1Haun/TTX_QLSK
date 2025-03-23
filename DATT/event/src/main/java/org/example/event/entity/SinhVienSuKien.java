package org.example.event.entity;



import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sinhvien_sukien")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SinhVienSuKien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Liên kết với sinh viên (nhiều sự kiện có thể thuộc 1 sinh viên)
    @ManyToOne
    @JoinColumn(name = "sinh_vien_id")
    private SinhVien sinhVien;

    private String theLoai;
    private String boMon;
    private String hocKy;
    private String block;
    private String chuyenNganh;

    private String mucTieuHuongDenSuKien;

    private LocalDateTime thoiGianBatDau;
}

