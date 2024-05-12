package controller;

import entity.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repository.ReservaRepository;

import java.util.List;


@RestController
@RequestMapping("/reservas")
public class ReservaController {
    @Autowired
    private ReservaRepository reservaRepository;

    @GetMapping
    public ResponseEntity<List<Reserva>> listar(){
        return ResponseEntity.ok(reservaRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Reserva> crear(@RequestBody Reserva reserva){
        return ResponseEntity.status(HttpStatus.CREATED).body(reservaRepository.save(reserva));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> obtener(@PathVariable Long id){
        return ResponseEntity.ok(reservaRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Error ")));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reserva> actualizar(@PathVariable Long id, @RequestBody Reserva reserva) {
        return ResponseEntity.ok(reservaRepository.findById(id).map(reservaDB -> {
            reservaDB.setCancha(reserva.getCancha());
            reservaDB.setUsuario(reserva.getUsuario());
            reservaDB.setFecha(reserva.getFecha());
            reservaDB.setEstado(reserva.getEstado());
            return reservaRepository.save(reservaDB);
        }).orElseThrow(() -> new IllegalArgumentException("Error ")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        reservaRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
