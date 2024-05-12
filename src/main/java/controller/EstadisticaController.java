package controller;


import entity.Estadistica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repository.EstadisticaRepository;

import java.util.List;

@RestController
@RequestMapping("/estadisticas")
public class EstadisticaController {
    @Autowired
    private EstadisticaRepository estadisticaRepository;

    @GetMapping
    public ResponseEntity<List<Estadistica>> listar() {
        return ResponseEntity.ok(estadisticaRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Estadistica> Crear(@RequestBody Estadistica estadistica) {
        return ResponseEntity.status(HttpStatus.CREATED).body(estadisticaRepository.save(estadistica));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estadistica> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(estadisticaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Error ")));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estadistica> actualizar(@PathVariable Long id, @RequestBody Estadistica estadistica) {
        return ResponseEntity.ok(estadisticaRepository.findById(id).map(estadisticaDB -> {
            estadisticaDB.setFecha(estadistica.getFecha());
            estadisticaDB.setReservasTotal(estadistica.getReservasTotal());
            estadisticaDB.setCanchasDisponibles(estadistica.getCanchasDisponibles());
            estadisticaDB.setHorasJugadas(estadistica.getHorasJugadas());
            return estadisticaRepository.save(estadisticaDB);
        }).orElseThrow(() -> new IllegalArgumentException("Error ")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        estadisticaRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
