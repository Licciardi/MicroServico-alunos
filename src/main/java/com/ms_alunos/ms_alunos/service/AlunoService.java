package com.ms_alunos.ms_alunos.service;

import com.ms_alunos.ms_alunos.dto.AlunoDTO;
import com.ms_alunos.ms_alunos.model.Aluno;
import com.ms_alunos.ms_alunos.repositories.AlunoRepository;
import com.ms_alunos.ms_alunos.service.exception.DatabaseException;
import com.ms_alunos.ms_alunos.service.exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AlunoService {

    @Autowired
    private AlunoRepository repository;

    @Transactional(readOnly = true)
    public Page<AlunoDTO> findAll(Pageable pageable){
        Page<Aluno> page = repository.findAll(pageable);
        return page.map(AlunoDTO::new);
    }

    @Transactional(readOnly = true)
    public AlunoDTO findById(Long id){
        Aluno entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado! Id: " + id)
        ) ;

        return new AlunoDTO(entity);
    }

    @Transactional
    public AlunoDTO insert (AlunoDTO dto){
        Aluno entity = new Aluno();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new AlunoDTO(entity);
    }

    @Transactional
    public AlunoDTO update (Long id, AlunoDTO dto){
        try{
            // não vai no DB, obj monitorado pela JPA
            Aluno entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new AlunoDTO(entity);
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Recurso não encontrado! Id: " + id);
        }
    }

    @Transactional
    public void delete(Long id){
        if(! repository.existsById(id)){
            throw new ResourceNotFoundException("Recurso não encontrado! Id: " + id);
        }
        try{
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e){
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

    private void copyDtoToEntity(AlunoDTO dto, Aluno entity) {
        entity.setNome(dto.getNome());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setRm(dto.getRm());
        entity.setStatus(dto.getStatus());
        entity.setTurma(dto.getTurma());
    }

}
