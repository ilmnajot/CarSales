package uz.ilmnajot.school_project.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uz.ilmnajot.school_project.entity.Warranty;
import uz.ilmnajot.school_project.enums.WarrantyType;
import uz.ilmnajot.school_project.repository.WarrantyRepository;

import java.time.LocalDateTime;

@Data
@Component
public class DataLoader implements CommandLineRunner {

    @Value("${spring.sql.init.mode}")
    private String mode;

    private final WarrantyRepository warrantyRepository;

    public DataLoader(WarrantyRepository warrantyRepository) {
        this.warrantyRepository = warrantyRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        if (mode.equals("always")) {
            warrantyRepository.save(
                    Warranty
                            .builder()
                            .id(1L)
                            .warrantyType(WarrantyType.ONE_YEAR_WARRANTY)
                            .description("this car is one-year warranty")
                            .build());

            warrantyRepository.save(
                    Warranty
                            .builder()
                            .id(2L)
                            .warrantyType(WarrantyType.TWO_YEAR_WARRANTY)
                            .description("this car is two-year warranty")
                            .build());

            warrantyRepository.save(
                    Warranty
                            .builder()
                            .id(3L)
                            .warrantyType(WarrantyType.THREE_YEAR_WARRANTY)
                            .description("this car is three-year warranty")
                            .build());


        }
    }
}
