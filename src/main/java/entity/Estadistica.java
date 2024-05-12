package entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "estadisticas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(of = {"id", "fecha", "reservasTotal", "canchasDisponibles", "horasJugadas"})
public class Estadistica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date fecha;

    @Column(nullable = false)
    private int reservasTotal;

    @Column(nullable = false)
    private int canchasDisponibles;

    @Column(nullable = false)
    private int horasJugadas;

    // Constructor, getters, setters, equals, hashCode, toString
}