package College.Library.Management.System.Project.Controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class AdminController {
    @GetMapping("/books")
    public String getBooks(){
        return "All books";
    }


    @GetMapping("/book")
    public String getBook(){
        return "All single book";
    }

    @PostMapping("/book")
    public String addBook(){
        return "add books";
    }
    @PutMapping("/book")
    public String updateBooks(){
        return "update books";
    }
    @DeleteMapping("/book")
    public String deleteBooks(){
        return "delete books";
    }


}
