package org.example.event.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "departments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoMon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", nullable = false, unique = true, length = 50)
    private String code;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "mail_lead", nullable = false, length = 255)
    private String mailLead;

    @Column(name = "name_lead", nullable = false, length = 255)
    private String nameLead;

    @Column(name = "status", nullable = false)
    private Boolean status;
}
