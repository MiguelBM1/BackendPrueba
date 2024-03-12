package com.example.backend.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "personas")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Personas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona")
    private Integer idPersona;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "departamento_id")
    private Integer departamentoId;

    @ManyToOne
    @JoinColumn(name = "departamento_id", insertable = false, updatable = false)
    @JsonBackReference
    private Departamentos departamentos;

    public Personas(Integer id) {
        this.idPersona = id;
    }
}
