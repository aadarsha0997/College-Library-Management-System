package College.Library.Management.System.Project.Service;

import College.Library.Management.System.Project.DTO.StudentCreateDTO;
import College.Library.Management.System.Project.DTO.StudentResponseDTO;
import College.Library.Management.System.Project.DTO.StudentUpdateDTO;
import College.Library.Management.System.Project.Model.Role;
import College.Library.Management.System.Project.Model.Student;
import College.Library.Management.System.Project.Repo.StudentRepo;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private  final StudentRepo repo;

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
    public StudentResponseDTO createUser(StudentCreateDTO userDetail) {
        Student student=new Student();
        student.setPassword(userDetail.getPassword());
        student.setName(userDetail.getName());
        student.setFaculty(userDetail.getFaculty());
        student.setSemester(userDetail.getSemester());
        student.setPhoneNumber(userDetail.getPhoneNumber());
        student.setRole(Role.STUDENT);
       Student savedUser= repo.save(student);
        savedUser.setStudentId(savedUser.getFaculty()+savedUser.getId());
        return mapToDTO(savedUser);

    }

    @Transactional
    public StudentResponseDTO updateUser(String studentId, StudentUpdateDTO userDetail) {
        System.out.println("Before updating");
        Student user=repo.findByStudentId(studentId).orElseThrow(()->new RuntimeException("Not found"));
        user.setName(userDetail.getName());
        user.setFaculty(userDetail.getFaculty());
        user.setSemester(userDetail.getSemester());
        user.setPhoneNumber(userDetail.getPhoneNumber());
        return mapToDTO(user);
    }


    public String deleteUser(String studentId) {
            Student user=repo.findByStudentId(studentId).orElseThrow(()->new RuntimeException("not found"));
            repo.delete(user);
            return "delete";

    }

}
