package com.ms_alunos.ms_alunos.repositories;

import com.ms_alunos.ms_alunos.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
