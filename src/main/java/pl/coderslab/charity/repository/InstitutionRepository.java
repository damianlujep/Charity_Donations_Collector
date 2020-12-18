package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.charity.entity.Institution;

public interface InstitutionRepository extends JpaRepository<Institution, Integer> {

}
