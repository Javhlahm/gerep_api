package com.llsoftwaresolutions.gerep_api.entidades;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "profesores")
@Data
public class Profesor extends Usuario {

    @OneToOne
    @JoinColumn(name = "grupo_id")
    private Grupo grupo;

}
