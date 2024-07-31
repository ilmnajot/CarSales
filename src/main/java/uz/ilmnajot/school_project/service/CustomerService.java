package uz.ilmnajot.school_project.service;

import uz.ilmnajot.school_project.model.common.ApiResponse;
import uz.ilmnajot.school_project.model.request.CustomerRequest;

public interface CustomerService {
    ApiResponse addCustomer(CustomerRequest request);
}
