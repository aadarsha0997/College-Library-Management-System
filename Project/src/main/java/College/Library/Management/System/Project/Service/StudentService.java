package College.Library.Management.System.Project.Service;

import College.Library.Management.System.Project.DTO.StudentCreateDTO;
import College.Library.Management.System.Project.DTO.StudentResponseDTO;
import College.Library.Management.System.Project.Model.Role;
import College.Library.Management.System.Project.Model.Student;
import College.Library.Management.System.Project.Repo.StudentRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepo repo;

    private StudentResponseDTO mapToDTO(Student student){
        StudentResponseDTO dto= new StudentResponseDTO();
        dto.setStudentId(student.getStudentId());
        dto.setName(student.getName());
        dto.setFaculty(student.getFaculty());
        dto.setSemester(student.getSemester());
        dto.setPhoneNumber(student.getPhoneNumber());
        return dto;
    }


    public StudentResponseDTO getUser(String studentId) {
                Student student=repo.findByStudentId(studentId).orElseThrow(() -> new RuntimeException("Student not found"));
        return mapToDTO(student);
    }


    public List<StudentResponseDTO> getAllUsers() {

        return repo.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Transactional
    public Student createUser(StudentCreateDTO userDetail) {
        Student student=new Student();
        student.setPassword(userDetail.getPassword());
        student.setName(userDetail.getName());
        student.setFaculty(userDetail.getFaculty());
        student.setSemester(userDetail.getSemester());
        student.setPhoneNumber(userDetail.getPhoneNumber());
        student.setRole(Role.STUDENT);
       Student savedUser= repo.save(student);
        savedUser.setStudentId(savedUser.getFaculty()+savedUser.getId());
        return savedUser;

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
