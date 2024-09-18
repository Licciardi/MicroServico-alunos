package com.ms_alunos.ms_alunos.Controller;


import com.ms_alunos.ms_alunos.dto.AlunoDTO;
import com.ms_alunos.ms_alunos.service.AlunoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService service;

    @GetMapping
    public ResponseEntity<Page<AlunoDTO>> findAll(
            @PageableDefault(size = 10) Pageable pageable) {

        Page<AlunoDTO> dto = service.findAll(pageable);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoDTO> findById(@PathVariable Long id) {
        AlunoDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<AlunoDTO> insert(@RequestBody @Valid AlunoDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto)
                .toUri();

        return ResponseEntity.created(uri).body(dto);
    }


    @PutMapping("/{id}")
    public ResponseEntity<AlunoDTO> update(@PathVariable @NotNull Long id,
                                               @RequestBody @Valid AlunoDTO dto ){
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
