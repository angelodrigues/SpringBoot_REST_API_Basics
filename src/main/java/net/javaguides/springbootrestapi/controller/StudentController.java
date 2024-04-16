package net.javaguides.springbootrestapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springbootrestapi.bean.Student;

@RestController
public class StudentController {

    @GetMapping("student")
    public Student getStudent(){
        Student student = new Student(1, "Angelo", "Barcelos");
        return student;
    }

    @GetMapping("students")
    public List<Student> getStudents(){
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "Angelo", "Barcelos"));
        students.add(new Student(2, "Yasmim", "Rodrigues"));
        students.add(new Student(3, "Hugo", "Rodrigues"));
        students.add(new Student(4, "Apolo", "Rodrigues"));

        return students;
    }

    @GetMapping("students/{id}/{first-name}/{last-name}")
    public Student studentPathVariable(@PathVariable("id") int studentId, 
                                        @PathVariable("first-name") String first, 
                                        @PathVariable("last-name") String last){
        Student student = new Student(studentId, first, last);
        return student;
    }

    @GetMapping("students/query")
    public Student studentRequestVariable(@RequestParam int id,
                                            @RequestParam String firstName,
                                            @RequestParam String lastName){
        return new Student(id, firstName, lastName);
    }
}