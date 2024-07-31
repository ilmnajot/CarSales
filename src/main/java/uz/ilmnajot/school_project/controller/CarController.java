package uz.ilmnajot.school_project.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.ilmnajot.school_project.enums.WarrantyType;
import uz.ilmnajot.school_project.model.common.ApiResponse;
import uz.ilmnajot.school_project.model.request.CarRequest;
import uz.ilmnajot.school_project.service.CarService;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/getAll") //working...
    public HttpEntity<ApiResponse> getAllCars() {
        ApiResponse apiResponse = carService.getAllCars();
        return apiResponse != null
                ? ResponseEntity.status(HttpStatus.OK).body(apiResponse)
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/getCar/{carId}") //working...
    public HttpEntity<ApiResponse> getCarById(@PathVariable(name = "carId") Long carId) {
        ApiResponse apiResponse = carService.getCar(carId);
        return apiResponse != null
                ? ResponseEntity.status(HttpStatus.OK).body(apiResponse)
                : ResponseEntity.notFound().build();

    }

    @PostMapping("/addCar") //working...
    public HttpEntity<ApiResponse> addCar(
            @RequestParam(name = "warrantyId") Long warrantyId,
            @RequestBody CarRequest request) {
        ApiResponse apiResponse = carService.addCar(warrantyId, request);
            return apiResponse != null
                ? ResponseEntity.status(HttpStatus.CREATED).body(apiResponse)
                : ResponseEntity.notFound().build();
    }

    @PostMapping("/sellCar") //working...
    public HttpEntity<ApiResponse> sellCarWithWarranty(@RequestParam(name = "carId") Long carId,
                                                       @RequestParam(name = "customerId") Long customerId) {
        ApiResponse apiResponse = carService.sellCar(carId, customerId);
        return apiResponse != null
                ? ResponseEntity.status(HttpStatus.CREATED).body(apiResponse)
                : ResponseEntity.notFound().build();
    }

    @PostMapping("/tradeInCar")
    public HttpEntity<ApiResponse> tradeInCar(@RequestParam(name = "oldCarId") Long oldCarId,
                                              @RequestParam(name = "newCarId") Long newCarId,
                                              @RequestParam(name = "customerId") Long customerId
    ) {
        ApiResponse apiResponse = carService.tradeInCar(oldCarId, newCarId, customerId);
        return apiResponse != null
                ? ResponseEntity.status(HttpStatus.CREATED).body(apiResponse)
                : ResponseEntity.notFound().build();
    }

    @PostMapping("/warranty")
    public HttpEntity<ApiResponse> purchaseWarranty(@RequestParam(name = "car") Long carId,
                                                    @RequestParam(name = "customerId") Long customerId,
                                                    @RequestParam(name = "warrantyId") Long warrantyId) {
        ApiResponse apiResponse = carService.purchaseWarranty(carId, customerId, warrantyId);
        return apiResponse != null
                ? ResponseEntity.status(HttpStatus.CREATED).body(apiResponse)
                : ResponseEntity.notFound().build();
    }


}
