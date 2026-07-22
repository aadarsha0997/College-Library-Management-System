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
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String bookId;

    private String name;

    private int totalCopies;

    private int availableCopies;

    @OneToMany(mappedBy = "book")
    @JsonIgnore
    private List<BorrowBook> borrowRecords= new ArrayList<>();


}
