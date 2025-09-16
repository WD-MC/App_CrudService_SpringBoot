package com.example.registration.employee.equipment.entities;

import com.example.registration.employee.equipment.enums.StatutEquipment;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "equipments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EquipmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private String employee;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatutEquipment status;

}
