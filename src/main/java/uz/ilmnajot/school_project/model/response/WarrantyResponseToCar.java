package uz.ilmnajot.school_project.model.response;

import lombok.Data;
import uz.ilmnajot.school_project.entity.Warranty;
import uz.ilmnajot.school_project.enums.WarrantyType;

import java.time.LocalDateTime;

@Data
public class WarrantyResponseToCar {


    private WarrantyType warrantyType;

    private String description;


    public WarrantyResponseToCar toWarrantyResponseToCar(Warranty warranty){
        WarrantyResponseToCar response = new WarrantyResponseToCar();
        response.setWarrantyType(warranty.getWarrantyType());
        response.setDescription(warranty.getDescription());
        return response;
    }


}
