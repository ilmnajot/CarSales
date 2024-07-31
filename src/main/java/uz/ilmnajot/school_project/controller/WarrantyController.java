package uz.ilmnajot.school_project.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.ilmnajot.school_project.model.common.ApiResponse;
import uz.ilmnajot.school_project.model.request.WarrantyRequest;
import uz.ilmnajot.school_project.service.WarrantyService;

@RestController
@RequestMapping("/warranty")
public class WarrantyController {

    private final WarrantyService warrantyService;

    public WarrantyController(WarrantyService warrantyService) {
        this.warrantyService = warrantyService;
    }
    @PostMapping("/addWarranty")
    public HttpEntity<ApiResponse> addWarranty(@RequestBody WarrantyRequest request){
        ApiResponse apiResponse = warrantyService.addWarranty(request);
        return apiResponse !=null
                ? ResponseEntity.status(HttpStatus.CREATED).body(apiResponse)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
