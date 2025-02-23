package com.llsoftwaresolutions.gerep_api.entidades;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "historiales_medicos")
@Data
public class HistorialMedico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String enfermedades;
    private String tipoSangre;
    private String alergias;
    private String cuidadosEspeciales;

    @OneToOne
    @JoinColumn(name = "alumno_id", unique = true)
    private Alumno alumno;
}
