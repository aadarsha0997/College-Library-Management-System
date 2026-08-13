package College.Library.Management.System.Project.Controller;
import College.Library.Management.System.Project.DTO.ReturnRecordDTO;
import College.Library.Management.System.Project.DTO.StudentCreateDTO;
import College.Library.Management.System.Project.DTO.StudentResponseDTO;
import College.Library.Management.System.Project.DTO.StudentUpdateDTO;
import College.Library.Management.System.Project.Model.BorrowBook;
import College.Library.Management.System.Project.Model.Student;
import College.Library.Management.System.Project.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService service;

    @PreAuthorize("hasRole('ADMIN') or #studentId==authentication.name")
    @GetMapping("/{studentId}")
    public StudentResponseDTO getUser(@PathVariable String studentId){
        return service.getUser(studentId);
    }

    @PreAuthorize("hasRole('ADMIN') or #studentId==authentication.name")
    @GetMapping("/{studentId}/history")
    public List<ReturnRecordDTO> getHistory(@PathVariable String studentId){
        return service.getHistory(studentId);
    }

    @PreAuthorize("hasRole('ADMIN') or #studentId==authentication.name")
    @GetMapping("/{studentId}/borrow-books")
    public List<ReturnRecordDTO> getBooks(@PathVariable String studentId){
        return service.getBooks(studentId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<StudentResponseDTO> getAllUsers(){
        return service.getAllUsers();
    }


    @PreAuthorize("hasRole('ADMIN') or #studentId==authentication.name")
    @PutMapping("/{studentId}")
    public StudentResponseDTO updateUser(
            @PathVariable String studentId,

            @Valid @RequestBody StudentUpdateDTO userDetail){
        return service.updateUser(studentId,userDetail);
    }

    @PreAuthorize("hasRole('ADMIN')")

    @DeleteMapping("/{studentId}")
    public String deleteUser(@PathVariable String studentId){
        return service.deleteUser(studentId);
    }


}