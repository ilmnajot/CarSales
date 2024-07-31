package uz.ilmnajot.school_project.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.ilmnajot.school_project.entity.Car;
import uz.ilmnajot.school_project.entity.Customer;
import uz.ilmnajot.school_project.entity.Warranty;
import uz.ilmnajot.school_project.enums.WarrantyType;
import uz.ilmnajot.school_project.exception.CarNotFoundException;
import uz.ilmnajot.school_project.exception.WarrantyNotFoundException;
import uz.ilmnajot.school_project.model.common.ApiResponse;
import uz.ilmnajot.school_project.model.request.CustomerRequest;
import uz.ilmnajot.school_project.model.response.CustomerResponse;
import uz.ilmnajot.school_project.repository.CarRepository;
import uz.ilmnajot.school_project.repository.CustomerRepository;
import uz.ilmnajot.school_project.repository.WarrantyRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final CarService carService;
    private final WarrantyRepository warrantyRepository;
    private final CarRepository carRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, CarService carService, WarrantyRepository warrantyRepository, CarRepository carRepository, CarRepository carRepository1) {
        this.customerRepository = customerRepository;
        this.carService = carService;
        this.warrantyRepository = warrantyRepository;
        this.carRepository = carRepository1;
    }

    @Override
    public ApiResponse addCustomer(CustomerRequest request) {
        Optional<Customer> existingCustomer = customerRepository.findByEmail(request.getEmail());
        if (existingCustomer.isPresent()) {
            throw new CarNotFoundException("Customer already registered with email: " + request.getEmail());
        }

        Customer customer = new Customer();
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setEmail(request.getEmail());
        customer.setPassword(request.getPassword());
        customer.setPhone(request.getPhone());
        customer.setAddress(request.getAddress());
        customer.setZipCode(request.getZipCode());

        Customer savedCustomer = customerRepository.save(customer);

        CustomerResponse customerResponse = new CustomerResponse().toCustomerResponse(savedCustomer);
        return new ApiResponse("success", true, customerResponse, HttpStatus.CREATED);


    }

    private Warranty getWarranty(Long warrantyId) {
        return warrantyRepository.findById(warrantyId).orElseThrow(()
                -> new WarrantyNotFoundException("warranty not found"));
    }
    private Car getCar(Long carId) {
        return carRepository.findById(carId).orElseThrow(()
                -> new WarrantyNotFoundException("car not found"));
    }

}
