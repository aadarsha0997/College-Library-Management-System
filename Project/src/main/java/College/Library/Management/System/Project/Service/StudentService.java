package College.Library.Management.System.Project.Service;

import College.Library.Management.System.Project.Model.Role;
import College.Library.Management.System.Project.Model.Student;
import College.Library.Management.System.Project.Repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepo repo;

    public Student getUser(String studentId) {
        return repo.findByStudentId(studentId).orElse(null);
    }


    public List<Student> getAllUsers() {
        return repo.findAll();
    }
    public Student createUser(Student userDetail) {
        userDetail.setRole(Role.STUDENT);
       Student savedUser= repo.save(userDetail);
        userDetail.setStudentId(savedUser.getFaculty()+savedUser.getId());
        return repo.save(savedUser);

    }
    public Student updateUser(String studentId,Student userDetail) {
        System.out.println("Before updating");
        Student user=repo.findByStudentId(studentId).orElseThrow(()->new RuntimeException("Not found"));
        user.setName(userDetail.getName());
        user.setFaculty(userDetail.getFaculty());
        user.setSemester(userDetail.getSemester());
        user.setPhoneNumber(userDetail.getPhoneNumber());

        return repo.save(user);
    }


    public String deleteUser(String studentId) {
            Student user=repo.findByStudentId(studentId).orElseThrow(()->new RuntimeException("not found"));
            repo.delete(user);
            return "delete";

    }

}
