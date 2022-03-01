package tajbanana.day2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tajbanana.day2.models.Task;
import tajbanana.day2.repositories.TaskRepository;
import tajbanana.day2.repositories.UserRepository;

import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    public Optional<Integer> addTask(Task task) {

        if (!userRepository.hasUser(task.getUsername()))
            return Optional.of(401);

        if (taskRepository.insertTask(task))
            return Optional.empty();

        return Optional.of(400);
    }
}
