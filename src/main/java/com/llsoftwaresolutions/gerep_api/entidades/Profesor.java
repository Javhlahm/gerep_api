package com.llsoftwaresolutions.gerep_api.entidades;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "profesores")
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Profesor extends Usuario {

    @OneToOne(mappedBy = "profesor", cascade = { CascadeType.PERSIST, CascadeType.MERGE }) // ← relación no propietaria
    @JsonIdentityReference(alwaysAsId = true)
    private Grupo grupo;
}
