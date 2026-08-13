package College.Library.Management.System.Project.Service;

import College.Library.Management.System.Project.DTO.*;
import College.Library.Management.System.Project.Exception.ResourceNotFound;
import College.Library.Management.System.Project.Model.BorrowBook;
import College.Library.Management.System.Project.Model.Role;
import College.Library.Management.System.Project.Model.Student;
import College.Library.Management.System.Project.Repo.RecordRepo;
import College.Library.Management.System.Project.Repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private  final StudentRepo repo;
    private  final PasswordEncoder passwordEncoder;

    @Autowired
    RecordRepo bookRepo;
    ReturnRecordDTO recordToReturnRecordDTO(BorrowBook record){
        ReturnRecordDTO data= new ReturnRecordDTO();
        data.setRecordId(record.getId());
        data.setBorrowAt(record.getBorrowAt());
        data.setReturnAt(record.getReturnAt());
        data.setStudentId(record.getStudent().getStudentId());
        data.setStudent(record.getStudent().getName());
        data.setBookId(record.getBook().getBookId());
        data.setBook(record.getBook().getName());
        return data;

    }

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
                Student student=repo.findByStudentId(studentId).orElseThrow(() -> new ResourceNotFound("Student not found"));
        return mapToDTO(student);
    }

    public Student getUserAuthentication(String studentId) {
             return repo.findByStudentId(studentId).orElseThrow(() -> new ResourceNotFound("Student not found"));
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
        student.setPassword(passwordEncoder.encode(userDetail.getPassword()));
        student.setName(userDetail.getName());
        student.setFaculty(userDetail.getFaculty());
        student.setSemester(userDetail.getSemester());
        student.setPhoneNumber(userDetail.getPhoneNumber());
        student.setRole(Role.STUDENT);
       Student savedUser= repo.save(student);
        savedUser.setStudentId(savedUser.getFaculty()+savedUser.getId());
        return savedUser;

    }

    @Transactional
    public StudentResponseDTO updateUser(String studentId, StudentUpdateDTO userDetail) {
        System.out.println("Before updating");
        Student user=repo.findByStudentId(studentId).orElseThrow(()->new ResourceNotFound("Not found"));
        user.setName(userDetail.getName());
        user.setFaculty(userDetail.getFaculty());
        user.setSemester(userDetail.getSemester());
        user.setPhoneNumber(userDetail.getPhoneNumber());
        return mapToDTO(user);
    }


    public String deleteUser(String studentId) {
            Student user=repo.findByStudentId(studentId).orElseThrow(()->new ResourceNotFound("not found"));
            repo.delete(user);
            return "User Deleted Successfully";

    }

    public List<ReturnRecordDTO> getHistory(String studentId) {

        return bookRepo.findByStudent_StudentId(studentId).stream().map(this::recordToReturnRecordDTO).toList();
    }

    public List<ReturnRecordDTO> getBooks(String studentId) {
        return bookRepo.findByStudent_StudentIdAndReturnAtIsNull(studentId).stream().map(this::recordToReturnRecordDTO).toList();
    }
}
