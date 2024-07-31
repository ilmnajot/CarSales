package uz.ilmnajot.school_project.service;

import uz.ilmnajot.school_project.model.common.ApiResponse;
import uz.ilmnajot.school_project.model.request.CarRequest;

public interface CarService {
    ApiResponse getAllCars();

    ApiResponse getCar(Long carId);

    ApiResponse addCar(Long warrantyId,CarRequest request);

    ApiResponse sellCar(Long carId, Long customerId);

    ApiResponse tradeInCar(Long oldCarId, Long newCarId, Long customerId);

    ApiResponse purchaseWarranty(Long carId, Long customerId, Long warrantyId);
}
