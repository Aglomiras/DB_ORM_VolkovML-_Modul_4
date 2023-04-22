package org.example.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.example.Models.Courses;
import org.example.Models.Groups_of_students;
import org.example.Models.Rating;
import org.example.Models.Student;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Repository
public class Student_Reliz_Repository implements Student_Interface_Repository {
    @PersistenceContext
    private final EntityManager entity_Manager;

    public Student_Reliz_Repository(EntityManager entityManager) {
        entity_Manager = entityManager;
    }

    @Override
    public List<Student> getAllStudent() {
        TypedQuery<Student> query = entity_Manager.createQuery("select s from Student s", Student.class);
        return query.getResultList();
    }

    @Override
    public String getAllStudent_List() {
        List<Student> stud = getAllStudent();
        String Result_Students = "";
        for(Student student: stud) {
            Result_Students += "ID студента - " + student.getId() + ", Фамилия и имя студента: " + student.getName_student() + ", академическая группа: " + student.getGroup_id().getName_group() + "\n";
        }
        return Result_Students;
    }

    @Override
    public List<Groups_of_students> getAllGroup() {
        TypedQuery<Groups_of_students> query = entity_Manager.createQuery("select s from Groups_of_students s", Groups_of_students.class);
        return query.getResultList();
    }

    @Override
    public String getAllGroup_List() {
        List<Groups_of_students> groups = getAllGroup();
        String Result_grops = "";
        for (Groups_of_students group: groups) {
            Result_grops += "ID группы - " + group.getId() + ", номер группы: " + group.getName_group() + "\n";
        }
        return Result_grops;
    }

    @Override
    public List<Courses> getAllCourses() {
        TypedQuery<Courses> query = entity_Manager.createQuery("select s from Courses s", Courses.class);
        return query.getResultList();
    }

    @Override
    public String getAllCourses_List() {
        List<Courses> courses = getAllCourses();
        String Result_courses = "";
        for (Courses cour: courses) {
            Result_courses += "ID курса - " + cour.getId() + ", название курса: " + cour.getName_courses() + "\n";
        }
        return Result_courses;
    }

    @Override
    public List<Rating> getAllRating() {
        TypedQuery<Rating> query = entity_Manager.createQuery("select s from Rating s", Rating.class);
        return query.getResultList();
    }

    @Override
    public String getAllRating_List() {
        List<Rating> ratings = getAllRating();
        String Result_rating = "";
        for (Rating rat: ratings) {
            Result_rating += "ID оценки - " + rat.getId() + ", оценка - " + rat.getRating() + "\n";
        }
        return Result_rating;
    }

    @Override
    public Map<String, Float> getAverage_Rating_Of_The_Group(int id) {
        List<Courses> cours_list = getAllCourses();
        List<Groups_of_students> group_list = getAllGroup();
        List<Student> student_list = getAllStudent();

        float summa_rating = 0;
        Map<String, Float> mapping_result = new HashMap<>();

        for (Courses cor: cours_list) {
            int count_student_group = 0;
            for (Student stu: student_list) {
                if (stu.getGroup_id().getId() == id) {
                    int count = 0;
                    for (Courses cor1: stu.getCourses()) {
                        count += 1;
                        if (cor1.getName_courses() == cor.getName_courses()) {
                            summa_rating += stu.getRating().get(count - 1).getRating();
                            count_student_group +=1;
                        }
                    }
                }
            }
            mapping_result.put(cor.getName_courses().toString(), summa_rating/(float)count_student_group);
            summa_rating = 0;
        }
        return mapping_result;
    }

    @Override
    public void Average(int id) {
        Map<String, Float> mapping_result = getAverage_Rating_Of_The_Group(id);
        List<Groups_of_students> group_list = getAllGroup();

        System.out.println("Средние баллы студентов по курсам группы " + group_list.get(id - 1).getName_group() + ":");

        System.out.println("Электромагнетизм - " + mapping_result.get("Electromagnetic"));
        System.out.println("Приводы - " + mapping_result.get("Privods"));
        System.out.println("РЗиАЭ - " + mapping_result.get("RZiAE"));
        System.out.println("Математика - " + mapping_result.get("Mathematics"));
        System.out.println("Станции и подстанции - " + mapping_result.get("Stantii_i_podstancii") + "\n");
    }
}
