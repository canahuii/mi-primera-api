package edu.umg.miprimeraapi.controladores;

import edu.umg.miprimeraapi.model.Estudiante;
import edu.umg.miprimeraapi.repository.EstudianteRepository;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;

@RestController // le indica al programa que esta clase es para hacer péticiones http
@RequestMapping("/api/estudiantes") // la ruta que usaremos para llamar a los estudiantes

public class EstudianteController
{
    private final EstudianteRepository repository;

    public EstudianteController(EstudianteRepository repository)
    {
        this.repository = repository;
    }

    //Get /api/estudiantes -> obtener todos los estudiantes.
    @GetMapping // retorna un valor (end point)
    public ResponseEntity<?> listar()
    {
        return ResponseEntity.ok(repository.findAll());
    }

    //GET /api/estudiantes/1 -> obtener estudiates por id
    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable Long id)
    {
        return repository.findById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("No encontrado"));
    }

    //POST /api/estudiantes -> crea un nuevo Estudiante
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Estudiante estudiante)
    {
        if (estudiante.getId() == null)
        {
            return ResponseEntity.badRequest().body("El id no puede ser nulo");
        }
        if (repository.existsById(estudiante.getId()))
        {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El estudiante ya existe");
        }

        Estudiante saved = repository.save(estudiante);
        return ResponseEntity.created(URI.create("//api/estudiantes/"+ saved.getId())).body(saved);
    }

    // PUT a/api/estudiantes/1 -> Actualizar un estudiante
    @PutMapping("/{id}")
    public ResponseEntity<?>    actualizar(@PathVariable Long id, @RequestBody Estudiante incoming)
    {
        return repository.findById(id)
                .<ResponseEntity<?>>map(e ->
                {
                    e.setNombre(incoming.getNombre());

                    Estudiante saved = repository.save(e);
                    return ResponseEntity.ok(saved);
                })

                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("No encontrado"));
    }

    //DELETE /api/estudiante/1
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id)
    {
        if(!repository.existsById(id))
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No encontrado");
        }

        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
