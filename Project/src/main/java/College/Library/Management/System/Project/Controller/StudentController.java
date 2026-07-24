package College.Library.Management.System.Project.Controller;
import College.Library.Management.System.Project.DTO.StudentCreateDTO;
import College.Library.Management.System.Project.DTO.StudentResponseDTO;
import College.Library.Management.System.Project.DTO.StudentUpdateDTO;
import College.Library.Management.System.Project.Model.BorrowBook;
import College.Library.Management.System.Project.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService service;

    @GetMapping("/{studentId}")
    public StudentResponseDTO getUser(@PathVariable String studentId){
        return service.getUser(studentId);
    }

    @GetMapping("/{studentId}/history")
    public List<BorrowBook> getHistory(@PathVariable String studentId){
        return service.getHistory(studentId);
    }

    @GetMapping("/{studentId}/borrow-books")
    public List<BorrowBook> getBooks(@PathVariable String studentId){
        return service.getBooks(studentId);
    }


    @GetMapping
    public List<StudentResponseDTO> getAllUsers(){
        return service.getAllUsers();
    }


    @PostMapping
    public StudentResponseDTO createUser(
            @Valid
            @RequestBody StudentCreateDTO userDetail){
        return service.createUser(userDetail);
    }


    @PutMapping("/{studentId}")
    public StudentResponseDTO updateUser(
            @PathVariable String studentId,

            @Valid @RequestBody StudentUpdateDTO userDetail){
        return service.updateUser(studentId,userDetail);
    }


    @DeleteMapping("/{studentId}")
    public String deleteUser(@PathVariable String studentId){
        return service.deleteUser(studentId);
    }


}