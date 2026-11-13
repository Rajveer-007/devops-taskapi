package com.example.taskapi.controller;
import com.example.taskapi.model.Task; import com.example.taskapi.service.TaskService;
import jakarta.validation.Valid; import jakarta.validation.constraints.NotBlank; import jakarta.validation.constraints.Size;
import java.net.URI; import java.util.List;
import org.springframework.http.ResponseEntity; import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/todos")
public class TaskController {
    private final TaskService taskService;
    public TaskController(TaskService s) { this.taskService = s; }
    @GetMapping public List<Task> list() { return taskService.findAll(); }
    @PostMapping public ResponseEntity<Task> create(@Valid @RequestBody CreateTaskRequest req) {
        Task created = taskService.create(req.title().trim()); return ResponseEntity.created(URI.create("/api/todos/" + created.getId())).body(created);
    }
    @GetMapping("/{id}") public ResponseEntity<Task> getById(@PathVariable Long id) {
        return taskService.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    public record CreateTaskRequest(@NotBlank @Size(max = 200) String title) {}
}
