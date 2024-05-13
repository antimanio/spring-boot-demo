package spring.demo.students.service;

import spring.demo.students.model.Student;
import spring.demo.students.repository.StudentRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentService {

    private final StudentRepo studentRepo;

    public List<Student> getAllStudents(){
        return studentRepo.findAll();
    }

    public Student getStudentById(Long id){
        return studentRepo.findById(id).orElse(null);
    }

    public Student createStudent (Student student){
        student.setCreatedAt(LocalDate.now());
        student.setUpdatedAt(LocalDate.now());
        Student createdStudent = studentRepo.save(student);
        return createdStudent;
    }

    public Student updateStudent (Long id, Student student) {
        Optional<Student> existingStudent = studentRepo.findById(id);

        if(existingStudent.isEmpty()) {
            return null;
        }

        student.setUpdatedAt(LocalDate.now());
        Student updatedStudent = studentRepo.save(student);
        return updatedStudent;
    }

    public boolean deleteStudentById (Long id) {
        Optional<Student> existingStudent = studentRepo.findById(id);
        if(existingStudent.isPresent()) {
            studentRepo.deleteById(id);
            return true;
        }
        return false;
    }

}