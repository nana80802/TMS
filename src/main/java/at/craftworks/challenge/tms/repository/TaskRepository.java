package at.craftworks.challenge.tms.repository;

import org.springframework.data.repository.CrudRepository;

import at.craftworks.challenge.tms.model.Task;

public interface TaskRepository extends CrudRepository<Task, Long>{

}
