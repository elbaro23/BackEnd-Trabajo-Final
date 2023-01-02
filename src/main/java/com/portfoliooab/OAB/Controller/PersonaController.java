
package com.portfoliooab.OAB.Controller;

import com.portfoliooab.OAB.Dto.dtoPersona;
import com.portfoliooab.OAB.Entity.Persona;
import com.portfoliooab.OAB.Security.Controller.Mensaje; 
import com.portfoliooab.OAB.Service.ImpPersonaService; 
import java.util.List;
import org.apache.commons.lang3.StringUtils; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/personas")
@CrossOrigin(origins = "https://frontendargprooab.web.app")
//@CrossOrigin(origins = {"https://frontendargprooab.web.app","http://localhost:4200"})
public class PersonaController {
    @Autowired
    ImpPersonaService personaService;

    @GetMapping("/lista")
    public ResponseEntity<List<Persona>> list() {
        List<Persona> list = personaService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Persona> getByID(@PathVariable("id") int id) {
        if (!personaService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.BAD_REQUEST);
        }

        Persona persona = personaService.getOne(id).get();
        return new ResponseEntity(persona, HttpStatus.OK);
    }

    /*@DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!personaService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        personaService.delete(id);
        return new ResponseEntity(new Mensaje("Educación eliminada"), HttpStatus.OK);
    }*/

    /*@PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoPersona dtopersona) {
        //El Nombre es obligatorio
        if (StringUtils.isBlank(dtopersona.getNombre())) {
            return new ResponseEntity(new Mensaje("El Nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        //El Nombre no se puede repetir
        if (personaService.existsByNombre(dtopersona.getNombre())) {
            return new ResponseEntity(new Mensaje("Ese Nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        //El Apellido es obligatorio (agregado - descomentar ImpPersonaService para que funcione)
        if (StringUtils.isBlank(dtopersona.getApellido())) {
            return new ResponseEntity(new Mensaje("El Apellido es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        //El Apellido no se puede repetir (agregado - descomentar ImpPersonaService para que funcione)
        if (personaService.existsByApellido(dtopersona.getApellido())) {
            return new ResponseEntity(new Mensaje("El Apellido ya existe"), HttpStatus.BAD_REQUEST);
        }
        //La Descripción es obligatorio (agregado)
        if (StringUtils.isBlank(dtopersona.getDescripcion())) {
            return new ResponseEntity(new Mensaje("La Descripción es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        //La Imagen es obligatorio (agregado)
        if (StringUtils.isBlank(dtopersona.getImg())) {
            return new ResponseEntity(new Mensaje("La Imagen es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        Persona persona = new Persona(dtopersona.getNombre(), dtopersona.getApellido(), dtopersona.getDescripcion(),dtopersona.getImg());
        personaService.save(persona);
        return new ResponseEntity(new Mensaje("Persona creada"), HttpStatus.OK);

    }*/

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoPersona dtopersona) {
        //Validamos si existe el ID
        if (!personaService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        //Compara nombre de experiencias
        if (personaService.existsByNombre(dtopersona.getNombre()) && personaService.getByNombre(dtopersona.getNombre()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        //El Nombre es obligatorio
        if(StringUtils.isBlank(dtopersona.getNombre())){
            return new ResponseEntity(new Mensaje("El campo Nombre no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }
        //El Apellido es obligatorio
        if(StringUtils.isBlank(dtopersona.getApellido())){
            return new ResponseEntity(new Mensaje("El campo Apellido no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }
        //La Descripción es obligatorio
        if (StringUtils.isBlank(dtopersona.getDescripcion())) {
            return new ResponseEntity(new Mensaje("El campo Descripción no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }
        //La Imagen es obligatorio
        if (StringUtils.isBlank(dtopersona.getImg())) {
            return new ResponseEntity(new Mensaje("El campo Imagen no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }
        
        Persona persona = personaService.getOne(id).get();
        persona.setNombre(dtopersona.getNombre());
        persona.setApellido(dtopersona.getApellido());
        persona.setDescripcion(dtopersona.getDescripcion());
        persona.setImg(dtopersona.getImg());

        personaService.save(persona);

        return new ResponseEntity(new Mensaje("Persona actualizada"), HttpStatus.OK);
    }
}
            
