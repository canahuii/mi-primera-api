package edu.umg.miprimeraapi.repository;

import edu.umg.miprimeraapi.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long > {
}
