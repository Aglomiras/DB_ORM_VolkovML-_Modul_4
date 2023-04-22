package org.example.Repository;

import org.example.Models.Courses;
import org.example.Models.Groups_of_students;
import org.example.Models.Rating;
import org.example.Models.Student;

import java.util.List;
import java.util.Map;

public interface Student_Interface_Repository {
    List<Student> getAllStudent();
    String getAllStudent_List();
    List<Groups_of_students> getAllGroup();
    String getAllGroup_List();
    List<Courses> getAllCourses();
    String getAllCourses_List();
    List<Rating> getAllRating();
    String getAllRating_List();

    Map<String, Float> getAverage_Rating_Of_The_Group(int average_rating);

    void Average(int id);
}
