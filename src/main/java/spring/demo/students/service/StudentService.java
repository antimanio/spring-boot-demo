package spring.demo.students.service;

import spring.demo.students.model.Student;
import spring.demo.students.repository.StudentRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Service layer is where all the business logic lies
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class StudentService {

    private final StudentRepo studentRepo;

    public List<Student> getAllStudents(){
        return studentRepo.findAll();
    }

    public Student getStudentById(Integer id){
        Optional<Student> student = studentRepo.findById(id);
        if(student.isPresent()){
            return student.get();
        }
        return null;
    }

    public Student createStudent (Student student){
        student.setCreatedAt(LocalDateTime.now());
        student.setUpdatedAt(LocalDateTime.now());
        Student createdStudent = studentRepo.save(student);
        return createdStudent;
    }

    public Student updateStudent (Student student) {
        Optional<Student> existingStudent = studentRepo.findById(student.getId());
        student.setCreatedAt(existingStudent.get().getCreatedAt());
        student.setUpdatedAt(LocalDateTime.now());

        Student updatedStudent = studentRepo.save(student);
        return updatedStudent;
    }

    public void deleteStudentById (Integer id) {
        studentRepo.deleteById(id);
    }

}