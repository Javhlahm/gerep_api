package com.llsoftwaresolutions.gerep_api.entidades;

import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "contactos_emergencia")
@Data
public class ContactoEmergencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String telefono;
    private String relacion;
    private String domicilio;

    @OneToMany(mappedBy = "contactoEmergencia")
    private List<Alumno> alumnos;
}
