package com.llsoftwaresolutions.gerep_api.entidades;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "usuarios")
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String telefono;

    private String direccion;

    @Column(unique = true)
    private String email;

    private String contrasena;

    private String rol;

}
