package edu.umg.miprimeraapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity // le indica a spring que esta clase pertenece a una tabla de base de datos
@Table(name = "estudiante") //especifica el nombre exacto de la tabla en la base de datos

public class Estudiante
{
    @Id //indica cual es la llave primaria en la tabla (id en este caso)
    private Long id;
    private String nombre;

    //constructor
    public Estudiante() {}

    //constructor con camps
    public Estudiante(Long id ,String nombre )
    {
        this.id = id;
        this.nombre = nombre;
    }

    // getter y setters
    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }
}
