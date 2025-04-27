package com.llsoftwaresolutions.gerep_api.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.llsoftwaresolutions.gerep_api.entidades.ContactoEmergencia;
import com.llsoftwaresolutions.gerep_api.repositorios.ContactoRepositorio;

@Service
public class ContactoEmergenciaServicio {

    @Autowired
    ContactoRepositorio contactoRepositorio;

    public List<ContactoEmergencia> listarContactos() {
        return contactoRepositorio.findAll();
    }

    public Optional<ContactoEmergencia> obtenerPorId(Long id) {
        return contactoRepositorio.findById(id);
    }

    public ContactoEmergencia guardarContacto(ContactoEmergencia contacto) {
        return contactoRepositorio.save(contacto);
    }

    public ContactoEmergencia actualizarContacto(Long id, ContactoEmergencia contactoActualizado) {
        Optional<ContactoEmergencia> contacto = contactoRepositorio.findById(id);
        if (contacto.isPresent()) {
            contacto.get().setNombre(contactoActualizado.getNombre());
            contacto.get().setTelefono(contactoActualizado.getTelefono());
            contacto.get().setRelacion(contactoActualizado.getRelacion());
            contacto.get().setDomicilio(contactoActualizado.getDomicilio());
            contacto.get().setAlumnos(contactoActualizado.getAlumnos());
            return contactoRepositorio.save(contacto.get());
        } else {
            throw new RuntimeException("Contacto no encontrado");
        }
    }

    public void eliminarContacto(Long id) {
        contactoRepositorio.deleteById(id);
    }
}
