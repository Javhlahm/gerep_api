package com.llsoftwaresolutions.gerep_api.controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.llsoftwaresolutions.gerep_api.entidades.ContactoEmergencia;
import com.llsoftwaresolutions.gerep_api.servicios.ContactoEmergenciaServicio;

@RestController
@RequestMapping("/contactos-emergencia")
@CrossOrigin(origins = "*")
public class ContactoEmergenciaControlador {

    @Autowired
    ContactoEmergenciaServicio contactoEmergenciaServicio;

    @GetMapping
    public ResponseEntity<List<ContactoEmergencia>> listarContactos() {
        return ResponseEntity.ok(contactoEmergenciaServicio.listarContactos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactoEmergencia> obtenerContacto(@PathVariable Long id) {
        Optional<ContactoEmergencia> contacto = contactoEmergenciaServicio.obtenerPorId(id);
        if (contacto.isPresent()) {
            return ResponseEntity.ok(contacto.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ContactoEmergencia> crearContacto(@RequestBody ContactoEmergencia contacto) {
        return ResponseEntity.ok(contactoEmergenciaServicio.guardarContacto(contacto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactoEmergencia> actualizarContacto(@PathVariable Long id,
            @RequestBody ContactoEmergencia contacto) {
        ContactoEmergencia actualizado = contactoEmergenciaServicio.actualizarContacto(id, contacto);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarContacto(@PathVariable Long id) {
        contactoEmergenciaServicio.eliminarContacto(id);
        return ResponseEntity.noContent().build();
    }
}
