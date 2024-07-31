package uz.ilmnajot.school_project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.ilmnajot.school_project.base.BaseEntity;

import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "CUSTOMER")
public class Customer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    @Pattern(regexp = "(^$|[0-9]{10})", message = "phoneNumber should be 10 digit")
    private String phone;

    private String address;

    @Pattern(regexp = "^$|[0-9]{6}", message = "zipCode should be 6 digit and letter")
    private String zipCode;

    @OneToMany
    private List<Car> cars = new ArrayList<>();

}
