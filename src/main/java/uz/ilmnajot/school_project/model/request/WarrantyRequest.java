package uz.ilmnajot.school_project.model.request;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import uz.ilmnajot.school_project.enums.WarrantyType;

import java.time.LocalDateTime;

@Data
public class WarrantyRequest {

    private WarrantyType warrantyType;

    private String description;

    private LocalDateTime validFrom;

    private LocalDateTime validTo;



}
