package College.Library.Management.System.Project.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(unique = true)
    private String studentId;

    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private   String faculty;
    @Column(nullable = false)
    private int semester;
    @Column(unique = true)
    private String phoneNumber;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<BorrowBook> borrowRecord= new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Role role;

}
