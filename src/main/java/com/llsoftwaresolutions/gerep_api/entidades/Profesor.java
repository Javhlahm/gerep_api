package com.llsoftwaresolutions.gerep_api.entidades;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "profesores")
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id") //poner esto siempre que vaya a haber relaciones para que no se cicle infinitamente

public class Profesor extends Usuario {

    @OneToOne
    @JoinColumn(name = "grupo_id", unique = true, nullable = true)
    @JsonIdentityReference(alwaysAsId = true) //poner esto siempre en un atributo con relacion para que no se cicle infinitamente
    private Grupo grupo;

}
