
package com.portfoliooab.OAB.Service;

import com.portfoliooab.OAB.Entity.Persona;
import com.portfoliooab.OAB.Interface.IPersonaService;
import com.portfoliooab.OAB.Repository.IPersonaRepository;
import java.util.List;
// import java.util.Optional; se habilita en capitulo 20
// import javax.transaction.Transactional; se habilita en capitulo 20
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImpPersonaService implements IPersonaService{
    @Autowired IPersonaRepository ipersonaRepository;
    
    @Override
    public List<Persona> getPersona() {
        List<Persona> persona = ipersonaRepository.findAll();
        return persona;
    }

    @Override
    public void savePersona(Persona persona) {
        ipersonaRepository.save(persona);
    }

    @Override
    public void deletePersona(Long id) {
        ipersonaRepository.deleteById(id);
    }

    @Override
    public Persona findPersona(Long id) {
        Persona persona = ipersonaRepository.findById(id).orElse(null);
        return persona;
    }
    
}


/* Correspondiente al capitulo 20


@Service
@Transactional
public class ImpPersonaService{
    
    @Autowired 
    IPersonaRepository ipersonaRepository;

    public List<Persona> list(){
        return ipersonaRepository.findAll();
    }
    
    public Optional<Persona> getOne(int id) {
        return ipersonaRepository.findById(id);
    }
    
    public Optional<Persona> getByNombre(String nombre) {
        return ipersonaRepository.findByNombre(nombre);
    }
    
    //public Optional<Persona> getByApellido(String apellido) { //agregado - descomentar en IPersonaRepository para que funcione
    //    return ipersonaRepository.findByApellido(apellido);
    //}

    public void save(Persona persona) {
        ipersonaRepository.save(persona);
    }
    
    public void delete(int id){
        ipersonaRepository.deleteById(id);
    }
    
    public boolean existsById(int id){
        return ipersonaRepository.existsById(id);  
    }
    
    public boolean existsByNombre(String nombre) {
        return ipersonaRepository.existsByNombre(nombre);
    }
    
    /*public boolean existsByApellido(String apellido) {       //agregado - descomentar en IPersonaRepository para que funcione
        return ipersonaRepository.existsByApellido(apellido);
    }*/

