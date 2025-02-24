package org.example.event.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ke_hoach_su_kien")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KeHoachSuKien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ten_ke_hoach", nullable = false, unique = true, length = 255)
    private String tenKeHoach;

    @Column(name = "bo_mon", nullable = false, length = 255)
    private String boMon;

    @Column(name = "hoc_ky", nullable = false, length = 255)
    private String hocKy;

    @Column(name = "trang_thai", nullable = false, length = 255)
    private String trangThai; // Trạng thái (ví dụ: "Đã duyệt", "Chờ duyệt")

    @Column(name = "lead_ke_hoach", nullable = false, length = 255)
    private String leadKeHoach;

    @Column(name = "so_luong_su_kien", nullable = false)
    private int soLuongSuKien;
}
