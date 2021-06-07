package at.craftworks.challenge.tms.service;

import java.util.List;

import at.craftworks.challenge.tms.model.Task;

public interface TaskService {

	public List<Task> getAllTasks();

	public Task getOneById(Long id) throws TaskNotFoundException;

	public Task createTask(Task task);

	public Task updateTask(Task task) throws TaskNotFoundException;

	public void deleteOneById(Long id) throws TaskNotFoundException;
}
