package College.Library.Management.System.Project.Controller;

import College.Library.Management.System.Project.DTO.BookCreateDTO;
import College.Library.Management.System.Project.DTO.BookResponseDTO;
import College.Library.Management.System.Project.DTO.BookUpdateDTO;
import College.Library.Management.System.Project.Service.BookService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/book")
@RestController
public class BookController {

    @Autowired
    BookService service;

    @GetMapping
    public List<BookResponseDTO> getBooks(){
        return service.getBooks();
    }


    @GetMapping("/{bookId}")
    public BookResponseDTO getBook(@PathVariable String bookId){
        return service.getBook(bookId);
    }

    @GetMapping("/search")
    public List<BookResponseDTO> searchBooks(@RequestParam String name) {
        return service.searchBook(name);
    }

    @PostMapping
    public BookResponseDTO addBook(@Valid @RequestBody BookCreateDTO bookDetail){
        return service.createBook(bookDetail);
    }
    @PutMapping("/{bookId}")
    public BookResponseDTO updateBooks(@PathVariable String bookId,
    @Valid @RequestBody BookUpdateDTO bookDetail){
        return service.bookUpdate(bookId,bookDetail);
    }


    @DeleteMapping("/{bookId}")
    public String deleteBooks(@PathVariable String bookId){
        return service.deleteBook(bookId);
    }


}
