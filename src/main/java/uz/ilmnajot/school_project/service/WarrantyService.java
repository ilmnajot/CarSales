package uz.ilmnajot.school_project.service;

import uz.ilmnajot.school_project.model.common.ApiResponse;
import uz.ilmnajot.school_project.model.request.WarrantyRequest;

public interface WarrantyService {
    ApiResponse addWarranty(WarrantyRequest request);
}
