package at.craftworks.challenge.tms.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.craftworks.challenge.tms.model.Task;
import at.craftworks.challenge.tms.repository.TaskRepository;

@Service
public class TaskserviceImpl implements TaskService {

	private static final Logger log = LoggerFactory.getLogger(TaskserviceImpl.class);

	@Autowired
	private TaskRepository taskRepository;

	private BlockingQueue<Task> taskQueue = new LinkedBlockingQueue<>();

	@Override
	public List<Task> getAllTasks() {
		return (List<Task>) taskRepository.findAll();
	}

	@Override
	public Task getOneById(Long id) throws TaskNotFoundException {
		Optional<Task> task = taskRepository.findById(id);
		if (task.isPresent()) {
			return task.get();
		}
		throw new TaskNotFoundException();
	}

	@Override
	public Task createTask(Task task) {
		log.info("createTask: {}", task.toString());
		taskQueue.add(task);
		return createOrUpdate(taskQueue.poll());
	}

	@Override
	public Task updateTask(Task task) throws TaskNotFoundException {
		if (isValidTask(task.getId())) {
			return createOrUpdate(task);
		}
		throw new TaskNotFoundException();
	}

	@Override
	public void deleteOneById(Long id) throws TaskNotFoundException {
		if (isValidTask(id)) {
			taskRepository.deleteById(id);
		} else {
			throw new TaskNotFoundException();
		}
	}

	private Task createOrUpdate(Task task) {
		Optional<Task> taskOpt = Optional.of(task);
		return taskRepository.save(taskOpt.get());
	}

	private boolean isValidTask(Long id) {
		Optional<Task> task = taskRepository.findById(id);
		return task.isPresent();

	}

}
