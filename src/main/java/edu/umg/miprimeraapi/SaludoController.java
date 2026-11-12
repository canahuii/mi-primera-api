package edu.umg.miprimeraapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")

public class SaludoController
{
    // end point 1: retorna un texto simple
    @GetMapping("/hola")
    public String saludar()
    {
        return "hola desconocido, te sludo desde mi primera api";
    }

    //end point 2:
    @GetMapping("/hola/{nombre}")
    public String saludarConNombre(@PathVariable String nombre)
    {
        return "hola " + nombre + " eres un desconocido aun asi quiero decirte que... TE QUIERO :,)";
    }
}
