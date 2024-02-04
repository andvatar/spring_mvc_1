package ru.javarush.tarasov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.javarush.tarasov.config.AppConfiguration;
import ru.javarush.tarasov.service.TaskService;
import ru.javarush.tarasov.service.TaskServiceImpl;

@SpringBootApplication
//@EnableJpaRepositories(basePackages={"ru.javarush.tarasov.repository"})
public class App {

    public static void main( String[] args ) {
  //      AnnotationConfigApplicationContext run = new AnnotationConfigApplicationContext(AppConfiguration.class);
        ConfigurableApplicationContext run = SpringApplication.run(App.class, args);

  //      TaskService service = run.getBean(TaskServiceImpl.class);


        //System.out.println(service.getTasks(1, 20).getContent());
    }

}
