package tajbanana.day2.controllers;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tajbanana.day2.repositories.TaskRepository;

@RestController
@RequestMapping(path = "/api/tasks")
public class TasksRestController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping
    public ResponseEntity<String> getTasks() {
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        taskRepository.getTasks().stream().forEach(v -> jsonArrayBuilder.add(v.toJson()));
        return ResponseEntity.ok(jsonArrayBuilder.build().toString());
    }
}
