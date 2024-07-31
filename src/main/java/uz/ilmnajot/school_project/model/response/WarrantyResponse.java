package uz.ilmnajot.school_project.model.response;

import lombok.Data;
import uz.ilmnajot.school_project.entity.Warranty;
import uz.ilmnajot.school_project.enums.WarrantyType;

import java.time.LocalDateTime;

@Data
public class WarrantyResponse {

    private Long id;

    private WarrantyType warrantyType;

    private String description;

    private LocalDateTime validFrom;

    private LocalDateTime validTo;

    public WarrantyResponse toWarrantyResponse(Warranty warranty){
        WarrantyResponse response = new WarrantyResponse();
        response.setId(warranty.getId());
        response.setWarrantyType(warranty.getWarrantyType());
        response.setDescription(warranty.getDescription());
        response.setValidFrom(warranty.getValidFrom());
        response.setValidTo(warranty.getValidTo());
        return response;
    }


}
