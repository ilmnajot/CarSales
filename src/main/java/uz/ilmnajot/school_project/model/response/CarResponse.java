package uz.ilmnajot.school_project.model.response;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import uz.ilmnajot.school_project.entity.Car;
import uz.ilmnajot.school_project.enums.Category;
import uz.ilmnajot.school_project.enums.Color;
import uz.ilmnajot.school_project.enums.WarrantyType;

import java.time.LocalDateTime;

@Data
public class CarResponse {


    private Long id;

    private String name;

    private String model;

    private Color color;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime productionDate;

    private String manufacturedCountry;

    private Category category;

    private Double price;

    private int numbers;

    private WarrantyType type;

//    private WarrantyType type;

    public CarResponse toCarResponse(Car car) {
        CarResponse response = new CarResponse();
        response.setId(car.getId());
        response.setName(car.getName());
        response.setModel(car.getModel());
        response.setColor(car.getColor());
        response.setProductionDate(car.getProductionDate());
        response.setManufacturedCountry(car.getManufacturedCountry());
        response.setCategory(car.getCategory());
        response.setPrice(car.getPrice());
        response.setNumbers(car.getNumbers());
        response.type(car.getWarranty().getWarrantyType());
        return response;
    }

    private void type(WarrantyType warrantyType) {
        this.type = warrantyType;
    }
}
