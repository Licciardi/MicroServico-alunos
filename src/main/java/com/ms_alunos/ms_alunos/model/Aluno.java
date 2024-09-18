package com.ms_alunos.ms_alunos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table( name = "tb_alunos")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "Campo requerido")
    private String nome;

    @NotNull(message = "Campo requerido")
    private String email;

    @NotNull(message = "Campo requerido")
    private String password;

    @NotNull(message = "Campo requerido")
    private String rm;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    private String turma;

}
