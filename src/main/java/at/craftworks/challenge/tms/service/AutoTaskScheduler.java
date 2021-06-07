package at.craftworks.challenge.tms.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.text.SimpleDateFormat;

import at.craftworks.challenge.tms.model.Task;

@Component
public class AutoTaskScheduler {

	private static final Logger log = LoggerFactory.getLogger(AutoTaskScheduler.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Autowired
	private TaskService taskService;

	@Scheduled(fixedRate = 15000)
	public void createRandomTask() {
		Task task = newTask();
		log.info("New task created by scheduler at {}", dateFormat.format(task.getCreatedAt()));
		taskService.createTask(task);
		log.info("Scheduler task {}", task.toString());
	}

	private Task newTask() {
		Task task = new Task();
		task.setCreatedAt(getCreatedAt());
		task.setDescription(generateAlphanumeric(30));
		task.setDueDate(getDueDate());
		task.setPriority(RandomStringUtils.random(1, "HML")); //H hight, M medium, L low
		task.setResolvedAt(getResolvedAt());
		task.setStatus(RandomStringUtils.random(1, "APR"));//A approved, P processing, R resolved
		task.setTitle(generateAlphanumeric(10));
		task.setUpdatedAt(getUpdatedAt());
		return task;
	}

	//add one day
	private Date getUpdatedAt() {
		return new Date(System.currentTimeMillis() + 86400000);
	}

	//add two days
	private Date getResolvedAt() {
		return new Date(System.currentTimeMillis() + 172800000);
	}

	//add 3 days
	private Date getDueDate() {
		return new Date(System.currentTimeMillis() + 259200000);
	}

	private Date getCreatedAt() {
		return new Date(System.currentTimeMillis());
	}

	private String generateAlphanumeric(int size) {
		return RandomStringUtils.randomAlphanumeric(size);
	}

}
