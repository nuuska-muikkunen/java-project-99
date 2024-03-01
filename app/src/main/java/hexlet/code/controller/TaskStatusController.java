package hexlet.code.controller;

import hexlet.code.dto.TaskStatusCreateDTO;
import hexlet.code.dto.TaskStatusDTO;
import hexlet.code.dto.TaskStatusUpdateDTO;
import hexlet.code.repository.TaskStatusRepository;
import hexlet.code.repository.UserRepository;
import hexlet.code.service.TaskStatusService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class TaskStatusController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskStatusRepository taskStatusRepository;

    @Autowired
    private TaskStatusService taskStatusService;

    @GetMapping(value = "/task_statuses")
    ResponseEntity<List<TaskStatusDTO>> index() {
        var taskStatuses = taskStatusService.getAll();
        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(taskStatuses.size()))
                .body(taskStatuses);
    }

    @GetMapping(value = "/task_statuses/{id}")
    TaskStatusDTO show(@PathVariable Long id) {
        return taskStatusService.findTaskStatus(id);
    }

    @PostMapping(value = "/task_statuses")
    @ResponseStatus(HttpStatus.CREATED)
    //@PreAuthorize("@userRepository.findByEmail(authentication.name).get().isEnabled()")
    TaskStatusDTO create(@Valid @RequestBody TaskStatusCreateDTO taskStatusData) {
        return taskStatusService.createTaskStatus(taskStatusData);
    }

    @PutMapping(path = "/task_statuses/{id}")
    @ResponseStatus(HttpStatus.OK)
    //@PreAuthorize("@userRepository.findById(#id).get().getEmail() == authentication.name")
    public TaskStatusDTO update(@Valid @RequestBody TaskStatusUpdateDTO taskStatusData, @PathVariable Long id) {
        return taskStatusService.updateTaskStatus(taskStatusData, id);
    }

    @DeleteMapping("/task_statuses/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    //@PreAuthorize("@userRepository.findById(#id).get().getEmail() == authentication.name")
    public void delete(@PathVariable Long id) {
        taskStatusService.deleteTaskStatus(id);
    }
}
