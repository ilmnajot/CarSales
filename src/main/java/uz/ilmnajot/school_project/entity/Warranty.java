package uz.ilmnajot.school_project.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.ilmnajot.school_project.base.BaseEntity;
import uz.ilmnajot.school_project.enums.WarrantyType;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "WARRANTY")
@Builder
public class Warranty extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private WarrantyType warrantyType;

    private String description;

//    private String price;

    private LocalDateTime validFrom;

    private LocalDateTime validTo;

}
