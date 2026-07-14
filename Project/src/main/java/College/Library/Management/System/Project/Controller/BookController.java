package College.Library.Management.System.Project.Controller;

import College.Library.Management.System.Project.DTO.BookCreateDTO;
import jakarta.validation.Valid;
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
    public BookCreateDTO addBook(@Valid @RequestBody BookCreateDTO bookDetail){
        return bookDetail;
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
