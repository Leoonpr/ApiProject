package com.projeto.backend.controllers;

import com.projeto.backend.entities.Task;
import com.projeto.backend.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks") // Corrigi o atributo 'name' para 'value' e adicionei uma barra inicial
public class TaskController {
    @Autowired
    private TaskRepository repository;

    @GetMapping
    public List<Task> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}") // Adicionei o caminho para o ID
    public Task findOne(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @PostMapping
    public Task insert(@RequestBody Task task) {
        return repository.save(task);
    }

    @DeleteMapping("/{id}") // Adicionei o caminho para o ID
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @PutMapping("/{id}") // Adicionei o caminho para o ID
    public void update(@RequestBody Task task, @PathVariable Long id) {
        // Verifique se a tarefa existe antes de atualizá-la
        if (repository.existsById(id)) {
            task.setId(id);
            repository.save(task);
        }
    }
}
