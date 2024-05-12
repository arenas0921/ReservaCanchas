package repository;

import entity.Cancha;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CanchaRepository extends JpaRepository<Cancha,Long> {
    Cancha findByNombre(String nombre);
}
