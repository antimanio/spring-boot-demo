package spring.demo.login.model;

import jakarta.persistence.*;

import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "refreshtoken")
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false)
    private LocalDate expirydate;

}
