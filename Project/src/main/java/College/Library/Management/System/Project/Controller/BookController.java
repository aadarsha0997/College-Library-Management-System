package College.Library.Management.System.Project.Controller;

import College.Library.Management.System.Project.DTO.BookCreateDTO;
import College.Library.Management.System.Project.Service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/book")
@RestController
public class BookController {

    @Autowired
    BookService service;

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
        return service.createBook(bookDetail);
    }
    @PutMapping("/{bookId}")
    public String updateBooks(@PathVariable String bookId){
        return "dd";
    }
    @DeleteMapping("/{bookId}")
    public String deleteBooks(@PathVariable String bookId){
        return bookId;
    }


}
