package uz.ilmnajot.school_project.model.response;

import lombok.Data;
import uz.ilmnajot.school_project.entity.Car;
import uz.ilmnajot.school_project.entity.Customer;
import uz.ilmnajot.school_project.model.request.CustomerRequest;

import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Data
public class CustomerResponse {


    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String address;

    private String zipCode;


    public CustomerResponse toCustomerResponse(Customer customer) {
        CustomerResponse response = new CustomerResponse();
        response.setId(customer.getId());
        response.setFirstName(customer.getFirstName());
        response.setLastName(customer.getLastName());
        response.setEmail(customer.getEmail());
        response.setPhone(customer.getPhone());
        response.setAddress(customer.getAddress());
        response.setZipCode(customer.getZipCode());
        return response;
    }


}
