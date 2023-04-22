package org.example.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name_student")
    private String name_student;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Groups_of_students group_id;

//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "student_rating",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "rating_id"))
    private List<Rating> rating;

//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @ManyToMany
    @JoinTable(name = "student_courses",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "courses_id"))
    private List<Courses> courses;
}
