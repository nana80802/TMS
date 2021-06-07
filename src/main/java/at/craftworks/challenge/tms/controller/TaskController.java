package at.craftworks.challenge.tms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import at.craftworks.challenge.tms.controller.model.TaskDto;
import at.craftworks.challenge.tms.service.TaskNotFoundException;
import at.craftworks.challenge.tms.service.TaskService;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

	@Autowired
	private TaskService taskService;

	@GetMapping("/all")
	public List<TaskDto> fetchAllTasks() {
		return new TaskDto().fromTasks(taskService.getAllTasks());
	};

	@GetMapping
	public TaskDto fetchAsingleTask(@RequestParam(required = true, name = "id") Long id) {
		try {
			return new TaskDto().fromTask(taskService.getOneById(id));
		} catch (TaskNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found ID: " + id, e);
		}
	};

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public TaskDto createAsingleTask(@RequestBody TaskDto taskDto) {
		return new TaskDto().fromTask(taskService.createTask(taskDto.toTask()));
	};

	@PutMapping
	public TaskDto updateAsingleTask(@RequestBody TaskDto taskDto) {
		try {
			return new TaskDto().fromTask(taskService.updateTask(taskDto.toTask()));
		} catch (TaskNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found ID: " + taskDto.getId(), e);
		}
	};

	@DeleteMapping
	public TaskDto deleteAsingleTask(@RequestParam(required = true, name = "id") Long id) {
		try {
			taskService.deleteOneById(id);
		} catch (TaskNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found ID: " + id, e);
		}
		return new TaskDto();
	};

}
