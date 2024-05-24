package spring.demo.haircutter.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cuttersqueue")
public class CuttersQueue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    private String estimatedTime;

    @NotNull
    @Min(value = 1)
    private Integer queueNumber;

}