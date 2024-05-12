package repository;

import entity.Estadistica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadisticaRepository extends JpaRepository<Estadistica, Long> {
}
