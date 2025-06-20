package com.llsoftwaresolutions.gerep_api.entidades;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "directores")
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Director extends Usuario {

    @OneToMany(mappedBy = "director", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Grupo> grupos;

}
