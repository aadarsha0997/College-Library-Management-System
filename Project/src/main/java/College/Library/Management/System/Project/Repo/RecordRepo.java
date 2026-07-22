package College.Library.Management.System.Project.Repo;

import College.Library.Management.System.Project.Model.BorrowBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordRepo extends JpaRepository< BorrowBook,Long> {

}
