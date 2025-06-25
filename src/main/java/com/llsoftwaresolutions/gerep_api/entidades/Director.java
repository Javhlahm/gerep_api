package com.llsoftwaresolutions.gerep_api.entidades;

import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "directores")
@Data
public class Director extends Usuario {

    @OneToMany(mappedBy = "director", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Grupo> grupos;

}
