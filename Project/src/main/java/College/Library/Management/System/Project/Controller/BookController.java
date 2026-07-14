package College.Library.Management.System.Project.Controller;

import org.springframework.web.bind.annotation.*;

@RequestMapping("/book")
@RestController
public class BookController {
    @GetMapping
    public String getBooks(){
        return "All books";
    }


    @GetMapping("/{bookId}")
    public String getBook(@PathVariable String bookId){
        return bookId;
    }

    @PostMapping
    public String addBook(){
        return "add books";
    }
    @PutMapping("/{bookId}")
    public String updateBooks(@PathVariable String bookId){
        return bookId;
    }
    @DeleteMapping("/{bookId}")
    public String deleteBooks(@PathVariable String bookId){
        return bookId;
    }


}
