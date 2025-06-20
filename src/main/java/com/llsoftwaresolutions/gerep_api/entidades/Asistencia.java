package com.llsoftwaresolutions.gerep_api.entidades;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "asistencias")
@Data
public class Asistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;

    private String estado;

    @ManyToOne
    @JoinColumn(name = "alumno_id")
    @JsonIdentityReference(alwaysAsId = true)
    private Alumno alumno;
}
