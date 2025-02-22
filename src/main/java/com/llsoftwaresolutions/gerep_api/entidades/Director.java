package com.llsoftwaresolutions.gerep_api.entidades;

import java.util.List;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "directores")
@Data
public class Director extends Usuario {

    @OneToMany(mappedBy = "director")
    private List<Grupo> grupos;

}
