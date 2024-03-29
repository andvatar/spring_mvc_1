package ru.javarush.tarasov.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import ru.javarush.tarasov.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
}
