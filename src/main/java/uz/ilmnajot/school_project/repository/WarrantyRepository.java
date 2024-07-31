package uz.ilmnajot.school_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.ilmnajot.school_project.entity.Warranty;
import uz.ilmnajot.school_project.enums.WarrantyType;

import java.util.Optional;

@Repository
public interface WarrantyRepository extends JpaRepository<Warranty, Long> {

    Optional<Warranty> findByWarrantyType(WarrantyType warrantyType);
}
