package controller;

import entity.Cancha;
import repository.CanchaRepository;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/canchas")
public class CanchaController {
    @Autowired
    private CanchaRepository canchaRepository;

    @GetMapping
    public ResponseEntity<List<Cancha>> listar() {
        return ResponseEntity.ok(canchaRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Cancha> crear(@RequestBody Cancha cancha) {
        return ResponseEntity.status(HttpStatus.CREATED).body(canchaRepository.save(cancha));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cancha> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(canchaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Error ")));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cancha> actualizar(@PathVariable Long id, @RequestBody Cancha cancha) {
        return ResponseEntity.ok(canchaRepository.findById(id).map(canchaDB -> {
            canchaDB.setNombre(cancha.getNombre());
            canchaDB.setTipo(cancha.getTipo());
            canchaDB.setCapacidad(cancha.getCapacidad());
            return canchaRepository.save(canchaDB);
        }).orElseThrow(() -> new IllegalArgumentException("Error ")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        canchaRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
