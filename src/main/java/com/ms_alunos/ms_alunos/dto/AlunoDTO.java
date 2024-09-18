package com.ms_alunos.ms_alunos.dto;

import com.ms_alunos.ms_alunos.model.Aluno;
import com.ms_alunos.ms_alunos.model.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class AlunoDTO {

    private long id;
    private String nome;
    private String email;
    private String password;
    private String rm;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String turma;

    public AlunoDTO(Aluno entity) {

        id = entity.getId();
        nome = entity.getNome();
        email = entity.getEmail();
        password = entity.getPassword();
        rm = entity.getRm();
        status = entity.getStatus();
        turma = entity.getTurma();

    }
}
