package uz.ilmnajot.school_project.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.ilmnajot.school_project.entity.Warranty;
import uz.ilmnajot.school_project.exception.WarrantyNotFoundException;
import uz.ilmnajot.school_project.model.common.ApiResponse;
import uz.ilmnajot.school_project.model.request.WarrantyRequest;
import uz.ilmnajot.school_project.model.response.WarrantyResponse;
import uz.ilmnajot.school_project.repository.WarrantyRepository;

import java.util.Optional;

@Service
public class WarrantyServiceImpl implements WarrantyService {

    private final WarrantyRepository warrantyRepository;

    public WarrantyServiceImpl(WarrantyRepository warrantyRepository) {
        this.warrantyRepository = warrantyRepository;
    }

    @Override
    public ApiResponse addWarranty(WarrantyRequest request) {
        Optional<Warranty> byWarrantyType = warrantyRepository.findByWarrantyType(request.getWarrantyType());
        if (byWarrantyType.isPresent()) {
            throw new WarrantyNotFoundException("this type of Warranty is already exists");
        }
        Warranty warranty = new Warranty();
        warranty.setWarrantyType(request.getWarrantyType());
        warranty.setDescription(request.getDescription());
        warranty.setValidFrom(request.getValidFrom());
        warranty.setValidTo(request.getValidTo());
        Warranty savedWarranty = warrantyRepository.save(warranty);
        WarrantyResponse warrantyResponse = new WarrantyResponse().toWarrantyResponse(savedWarranty);
        return new ApiResponse("success", true, warrantyResponse, HttpStatus.CREATED);
    }
}
