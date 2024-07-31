package uz.ilmnajot.school_project.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.ilmnajot.school_project.entity.Car;
import uz.ilmnajot.school_project.entity.Customer;
import uz.ilmnajot.school_project.entity.Warranty;
import uz.ilmnajot.school_project.enums.WarrantyType;
import uz.ilmnajot.school_project.exception.CarNotFoundException;
import uz.ilmnajot.school_project.exception.CustomerNotFoundException;
import uz.ilmnajot.school_project.exception.WarrantyNotFoundException;
import uz.ilmnajot.school_project.model.common.ApiResponse;
import uz.ilmnajot.school_project.model.request.CarRequest;
import uz.ilmnajot.school_project.model.response.CarResponse;
import uz.ilmnajot.school_project.repository.CarRepository;
import uz.ilmnajot.school_project.repository.CustomerRepository;
import uz.ilmnajot.school_project.repository.WarrantyRepository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    private final CustomerRepository customerRepository;

    private final WarrantyRepository warrantyRepository;

    public CarServiceImpl(CarRepository carRepository, CustomerRepository customerRepository, WarrantyRepository warrantyRepository) {
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
        this.warrantyRepository = warrantyRepository;
    }

    @Override
    public ApiResponse getAllCars() {
        List<Car> cars = carRepository.findAll();
        if (cars.isEmpty()) {
            throw new CarNotFoundException("cars not found");
        }
        List<CarResponse> list = cars.stream().map(car -> new CarResponse().toCarResponse(car)).toList();
        Map<String, Object> carResp = new HashMap<>();
        carResp.put("list of cars", list);
        carResp.put("page", 0);
        carResp.put("number of cars", list.size());
        return new ApiResponse("success", true, carResp, HttpStatus.OK);
    }

    @Override
    public ApiResponse getCar(Long carId) {
        Car car = getCarById(carId);
        CarResponse carResponse = new CarResponse().toCarResponse(car);
        return new ApiResponse("success", true, carResponse, HttpStatus.FOUND);
    }

    @Override
    public ApiResponse addCar(Long warrantyId, CarRequest request) {
        Optional<Car> optionalCar = carRepository.findByModel(request.getModel());
        if (optionalCar.isPresent()) {
            throw new CarNotFoundException("Car model is already exists");
        }
        Warranty warranty = getWarrantyById(warrantyId);

        Car car = new Car();
        car.setName(request.getName());
        car.setModel(request.getModel());
        car.setColor(request.getColor());
        car.setProductionDate(request.getProductionDate());
        car.setManufacturedCountry(request.getManufacturedCountry());
        car.setCategory(request.getCategory());
        car.setPrice(request.getPrice());
        car.setNumbers(request.getNumbers());
        car.setWarranty(warranty);

        Car savedCars = carRepository.save(car);
        CarResponse carResponse = new CarResponse().toCarResponse(savedCars);

        return new ApiResponse("success", true, carResponse);
    }

    @Override
    public ApiResponse sellCar(Long carId, Long customerId) {
        Car car = getCarById(carId);
        Customer customer = getCustomerById(customerId);

        Long warrantyId = car.getWarranty().getId();
        Warranty warranty = getWarrantyById(warrantyId);
        WarrantyType type = warranty.getWarrantyType();

        if (car.getNumbers() <= 0) {
            throw new CarNotFoundException("car is out of stock");
        }
        LocalDateTime now = LocalDateTime.now();

        switch (type) {
            case ONE_YEAR_WARRANTY:
                warranty.setValidFrom(now);
                warranty.setValidTo(now.plusYears(1));
                break;
            case TWO_YEAR_WARRANTY:
                warranty.setValidFrom(now);
                warranty.setValidTo(now.plusYears(2));
                break;
            case THREE_YEAR_WARRANTY:
                warranty.setValidFrom(now);
                warranty.setValidTo(now.plusYears(3));
                break;
            default:
                throw new IllegalArgumentException("Invalid warranty exception");
        }

        car.setNumbers(car.getNumbers() - 1);
        car.setWarranty(warranty);
        carRepository.save(car);

        customer.getCars().add(car);
        customerRepository.save(customer);

        return new ApiResponse("success", true, "the car has been sold. car ID: " + carId + " customer ID: " + customer.getId());

    }

    @Override
    public ApiResponse tradeInCar(Long oldCarId, Long newCarId, Long customerId) {
        Car oldCar = getCarById(oldCarId);
        Car newCar = getCarById(newCarId);
        Customer customer = getCustomerById(customerId);

        double tradeInValue = evaluateCar(oldCar);
        double calculatedDiscount = calculateDiscount(tradeInValue, newCar.getPrice());
        double finalPrice = newCar.getPrice() - calculatedDiscount;
        oldCar.setNumbers(oldCar.getNumbers() + 1);
        newCar.setNumbers(newCar.getNumbers() - 1);
        carRepository.save(oldCar);
        carRepository.save(newCar);


        return null;
    }

    @Override
    public ApiResponse purchaseWarranty(Long carId, Long customerId, Long warrantyId) {
        Car car = getCarById(carId);
        Customer customer = getCustomerById(customerId);
        Warranty warranty = getWarrantyById(warrantyId);
        car.setWarranty(warranty);
        carRepository.save(car);
        return null;
    }

    private double evaluateCar(Car car) {
        return car.getPrice() * 0.8;
    }

    private double calculateDiscount(double tradeInValue, double newCarPrice) {
        return Math.min(tradeInValue, newCarPrice * 0.5);
    }

    private Warranty getWarrantyById(Long warrantyId) {
        return warrantyRepository.findById(warrantyId).orElseThrow(()
                -> new WarrantyNotFoundException("warranty not found"));
    }

    private Car getCarById(Long carId) {
        return carRepository.findById(carId).orElseThrow(()
                -> new CarNotFoundException("car not found"));
    }

    private Customer getCustomerById(Long customerId) {
        return customerRepository.findById(customerId).orElseThrow(()
                -> new CustomerNotFoundException("customer not found"));
    }

}
