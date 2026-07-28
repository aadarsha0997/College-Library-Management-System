package College.Library.Management.System.Project.Repo;

import College.Library.Management.System.Project.Model.Book;
import College.Library.Management.System.Project.Model.BorrowBook;
import College.Library.Management.System.Project.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecordRepo extends JpaRepository< BorrowBook,Long> {

    List<BorrowBook> findByStudent_StudentId(String studentId);
    List<BorrowBook> findByStudent_StudentIdAndReturnAtIsNull(String studentId);
    Long countByStudentAndReturnAtIsNull(Student student);
    boolean existsByStudentAndBookAndReturnAtIsNull(Student student, Book book);
}
