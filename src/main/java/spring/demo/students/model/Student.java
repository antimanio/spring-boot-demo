package spring.demo.students.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @Min(value = 0)
    @Max(value = 100)
    private Integer age;

    @Email
    private String email;

    @NotNull
    private String address;

    @PastOrPresent
    private LocalDate dateOfBirth;

    @NotNull
    private LocalDate createdAt;

    @NotNull
    private LocalDate updatedAt;

}