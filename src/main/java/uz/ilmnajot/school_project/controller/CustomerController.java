package uz.ilmnajot.school_project.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.ilmnajot.school_project.model.common.ApiResponse;
import uz.ilmnajot.school_project.model.request.CustomerRequest;
import uz.ilmnajot.school_project.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/addCustomer")
    public HttpEntity<ApiResponse> addCustomer(
            @RequestBody CustomerRequest request) {
        ApiResponse apiResponse = customerService.addCustomer(request);
        return apiResponse != null
                ? ResponseEntity.status(HttpStatus.CREATED).body(apiResponse)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
    }


}
