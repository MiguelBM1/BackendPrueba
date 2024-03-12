package com.example.backend.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "estados")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Estados {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado")
    private Integer idEstado;

    @Column(name = "nombre")
    private String nombre;

    public Estados(Integer id) {
        this.idEstado = id;
    }

}
