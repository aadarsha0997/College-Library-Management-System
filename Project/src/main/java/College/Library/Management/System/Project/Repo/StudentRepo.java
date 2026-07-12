package College.Library.Management.System.Project.Repo;

import College.Library.Management.System.Project.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {

    Optional <Student > findByStudentId(String studentId);

}
