package at.craftworks.challenge.tms.controller.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import at.craftworks.challenge.tms.model.Task;

public class TaskDto {
	private Long id;
	private Date createdAt;
	private Date updatedAt;
	private Date dueDate;
	private Date resolvedAt;
	private String title;
	private String description;
	private String priority;
	private String status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getResolvedAt() {
		return resolvedAt;
	}

	public void setResolvedAt(Date resolvedAt) {
		this.resolvedAt = resolvedAt;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public TaskDto fromTask(Task task) {
		TaskDto taskDto = new TaskDto();
		taskDto.setCreatedAt(task.getCreatedAt());
		taskDto.setDescription(task.getDescription());
		taskDto.setDueDate(task.getDueDate());
		taskDto.setId(task.getId());
		taskDto.setPriority(task.getPriority());
		taskDto.setResolvedAt(task.getResolvedAt());
		taskDto.setStatus(task.getStatus());
		taskDto.setTitle(task.getTitle());
		taskDto.setUpdatedAt(task.getUpdatedAt());
		return taskDto;
	};

	public List<TaskDto> fromTasks(List<Task> tasks) {
		List<TaskDto> taskDtos = new ArrayList<TaskDto>();
		for (Task task : tasks) {
			taskDtos.add(fromTask(task));
		}
		return taskDtos;
	};

	public Task toTask() {
		Task task = new Task();
		task.setCreatedAt(getCreatedAt());
		task.setDescription(getDescription());
		task.setDueDate(getDueDate());
		task.setId(getId());
		task.setPriority(getPriority());
		task.setResolvedAt(getResolvedAt());
		task.setStatus(getStatus());
		task.setTitle(getTitle());
		task.setUpdatedAt(getUpdatedAt());
		return task;
	}
}
