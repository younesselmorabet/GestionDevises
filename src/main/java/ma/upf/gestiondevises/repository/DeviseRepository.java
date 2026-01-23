package ma.upf.gestiondevises.repository;

import ma.upf.gestiondevises.entities.Devise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviseRepository extends JpaRepository<Devise, Long> {
    Devise findByCode(String code);
}