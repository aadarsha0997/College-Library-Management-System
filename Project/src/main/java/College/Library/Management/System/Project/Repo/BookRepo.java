package College.Library.Management.System.Project.Repo;

import College.Library.Management.System.Project.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepo extends JpaRepository<Book,Long> {
    Optional<Book> findByBookId(String bookId);
}
