package uz.ilmnajot.school_project.model.request;

import lombok.Data;
import uz.ilmnajot.school_project.entity.Warranty;
import uz.ilmnajot.school_project.enums.Category;
import uz.ilmnajot.school_project.enums.Color;
import uz.ilmnajot.school_project.enums.WarrantyType;
import uz.ilmnajot.school_project.model.response.WarrantyResponseToCar;

import java.time.LocalDateTime;

@Data
public class CarRequest {

    private String name;

    private String model;

    private Color color;

    private LocalDateTime productionDate;

    private String manufacturedCountry;

    private Category category;

    private Double price;

    private int numbers;

    private WarrantyType type;
}
