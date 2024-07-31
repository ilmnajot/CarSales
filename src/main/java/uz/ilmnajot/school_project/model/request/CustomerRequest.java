package uz.ilmnajot.school_project.model.request;

import jakarta.persistence.OneToMany;
import lombok.Data;
import uz.ilmnajot.school_project.entity.Car;

import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Data
public class CustomerRequest {


    private String firstName;

    private String lastName;

    private String email;

    private String password;

    @Pattern(regexp = "(^$|[0-9]{10})", message = "phoneNumber should be 10 digit")
    private String phone;

    private String address;

    @Pattern(regexp = "^$|[0-9]{6}", message = "zipCode should be 6 digit and letter")
    private String zipCode;



}
