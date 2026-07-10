package College.Library.Management.System.Project.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    @GetMapping("/dashbord")
    public String myDetail(){
        return "Your Detail";

    }

    @GetMapping("/mybooks")
    public String myBooks(){
        return "MyBooks";
    }

    @GetMapping("/search")
    public String booksAvailable(){
        return "Searching...";
    }

    @GetMapping("/myhistory")
    public String myHistory(){
        return "MyHistory";
    }
}