package net.javaguides.springbootrestapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springbootrestapi.bean.Student;

@RestController
@RequestMapping("students")
public class StudentController {

    @GetMapping
    public ResponseEntity<Student> getStudent(){
        Student student = new Student(1, "Angelo", "Barcelos");
        //#1
        //return ResponseEntity.ok(student);
        //#2
        //return new ResponseEntity<>(student, HttpStatus.OK);
        //#3
        return ResponseEntity.ok()
                                .header("custom-header", "Angelo")
                                .body(student);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getStudents(){
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "Angelo", "Barcelos"));
        students.add(new Student(2, "Yasmim", "Rodrigues"));
        students.add(new Student(3, "Hugo", "Rodrigues"));
        students.add(new Student(4, "Apolo", "Rodrigues"));

        return ResponseEntity.ok(students);
    }

    @GetMapping("{id}/{first-name}/{last-name}")
    public ResponseEntity<Student> studentPathVariable(@PathVariable("id") int studentId, 
                                        @PathVariable("first-name") String first, 
                                        @PathVariable("last-name") String last){
        Student student = new Student(studentId, first, last);
        return ResponseEntity.ok(student);
    }

    @GetMapping("query")
    public ResponseEntity<Student> studentRequestVariable(@RequestParam int id,
                                            @RequestParam String firstName,
                                            @RequestParam String lastName){
        Student student = new Student(id, firstName, lastName);
        return ResponseEntity.ok(student);
    }

    @PostMapping("create")
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        //#1
        //return new ResponseEntity<>(student, HttpStatus.CREATED);
        //#2
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }

    @PutMapping("{id}/update")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable int id){
        List<Student> students = (List<Student>) this.getStudents();

        for (Student student2 : students) {
            if(student2.getId() == id){
                System.out.println(student2.getId());
                System.out.println(student2.getFirstName());
                System.out.println(student2.getLastName());

                student2.setFirstName(student.getFirstName());
                student2.setLastName(student.getLastName());

                System.out.println(student2.getId());
                System.out.println(student2.getFirstName());
                System.out.println(student2.getLastName());
                return ResponseEntity.ok(student2);
            }
        }

        return null;
    }

    @DeleteMapping("{id}/delete")
    public ResponseEntity<List<Student>> deleteStudent(@PathVariable int id) {
        List<Student> students = (List<Student>) this.getStudents();
    
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            if (student.getId() == id) {
                students.remove(i); 
                return ResponseEntity.ok(students);
            }
        }
        
        return null;
    }
}