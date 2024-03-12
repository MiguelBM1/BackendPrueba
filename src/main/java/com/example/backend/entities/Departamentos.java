package com.example.backend.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "departamentos")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Departamentos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_departamento")
    private Integer idDepartamento;




}
