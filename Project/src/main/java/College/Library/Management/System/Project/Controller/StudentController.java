package College.Library.Management.System.Project.Controller;

import College.Library.Management.System.Project.Model.Student;
import College.Library.Management.System.Project.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    StudentService service;

    @GetMapping("/user/{studentId}")
    public Student getUser(@PathVariable String studentId){
         System.out.println("Hi");
        return service.getUser(studentId);
    }
    @GetMapping("/users")
    public List<Student> getAllUsers(){
        return service.getAllUsers();

    }
    @PostMapping("/user")
    public Student createUser(
            @RequestBody Student userDetail){
        System.out.println(userDetail);
        return service.createUser(userDetail);

    }
    @PutMapping("/user/{studentId}")
    public Student updateUser(
            @PathVariable String studentId,
            @RequestBody Student userDetail){
        return service.updateUser(studentId,userDetail);
    }
    @DeleteMapping("/user/{studentId}")
    public String deleteUser(@PathVariable String studentId){
        return service.deleteUser(studentId);

    }


}