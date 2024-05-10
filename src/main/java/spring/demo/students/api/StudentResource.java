package spring.demo.students.api;

import spring.demo.students.model.Student;
import spring.demo.students.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/students/v1")
@RequiredArgsConstructor
@Validated
public class StudentResource {

    private final StudentService studentService;


    @GetMapping("/")
    public ResponseEntity<List<Student>> getAllStudents(){
        return ResponseEntity.ok().body(studentService.getAllStudents());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Integer id)
    {
        return ResponseEntity.ok().body(studentService.getStudentById(id));
    }

    @PostMapping("/")
    public ResponseEntity<Student> createStudent(@RequestBody Student student)
    {
        return ResponseEntity.ok().body(studentService.createStudent(student));
    }


    @PutMapping("/")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student)
    {
        return ResponseEntity.ok().body(studentService.updateStudent(student));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable Integer id)
    {
        studentService.deleteStudentById(id);
        return ResponseEntity.ok().body("Deleted Student number: " + id +  " successfully");
    }


}
