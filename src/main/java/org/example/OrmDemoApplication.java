package org.example;

import org.h2.tools.Console;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.Repository.Student_Interface_Repository;
import org.example.Repository.Student_Reliz_Repository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.sql.SQLException;
@SpringBootApplication
public class OrmDemoApplication {
    public static void main(String[] args) throws SQLException {
        ConfigurableApplicationContext applicationContext =
                SpringApplication.run(OrmDemoApplication.class, args);

        EntityManagerFactory factory = applicationContext.getBean(EntityManagerFactory.class);
        EntityManager entityManager = factory.createEntityManager();
        Student_Interface_Repository stuInRep = new Student_Reliz_Repository(entityManager);

        System.out.println(stuInRep.getAllStudent_List());
        System.out.println(stuInRep.getAllGroup_List());
        System.out.println(stuInRep.getAllCourses_List());
        System.out.println(stuInRep.getAllRating_List());

        stuInRep.Average(1);
        stuInRep.Average(2);
        stuInRep.Average(3);
        stuInRep.Average(4);
        stuInRep.Average(5);

        Console.main(args);

//        System.out.println(stuInRep.getAllStudent_List());
    }
}