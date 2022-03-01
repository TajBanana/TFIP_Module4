package tajbanana.day2.controllers;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tajbanana.day2.models.Task;
import tajbanana.day2.services.TaskService;

import javax.swing.text.html.Option;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/task")
public class TaskRestController {

    @Autowired
    private TaskService taskService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postTask (@RequestBody String payload) {
        System.out.println("payload >>>> " + payload);

        Task task = null;
        JsonObject error;

        try {
            task = Task.create(payload);
        } catch (Exception e) {
            System.out.println("get error message >>>> " + e.getMessage());
            error = Json.createObjectBuilder()
                    .add("error",e.getMessage())
                    .build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.toString());
        }

        Optional<Integer> opt = taskService.addTask(task);

        if (opt.isEmpty())
            return ResponseEntity.ok("{}");

        switch (opt.get()) {
            case 401:
                error = Json.createObjectBuilder()
                        .add("error","Username %s not found")
                        .build();
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error.toString());

            default:
                error = Json.createObjectBuilder()
                        .add("error", "Failed to create new task")
                        .build();
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.toString());
        }
    }

}
