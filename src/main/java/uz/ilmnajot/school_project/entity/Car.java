package uz.ilmnajot.school_project.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;
import uz.ilmnajot.school_project.base.BaseEntity;
import uz.ilmnajot.school_project.enums.Category;
import uz.ilmnajot.school_project.enums.Color;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="CARS")
public class Car extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String model;

    @Enumerated(value = EnumType.STRING)
    private Color color;

    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime productionDate;

    private String manufacturedCountry;

    @Enumerated(value = EnumType.STRING)
    private Category category;

    private Double price;

    private int numbers;

    @ManyToOne
    private Warranty warranty;

}
