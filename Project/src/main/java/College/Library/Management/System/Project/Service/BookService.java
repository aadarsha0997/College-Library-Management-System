package College.Library.Management.System.Project.Service;

import College.Library.Management.System.Project.DTO.BookCreateDTO;
import College.Library.Management.System.Project.DTO.BookResponseDTO;
import College.Library.Management.System.Project.DTO.BookUpdateDTO;
import College.Library.Management.System.Project.Model.Book;
import College.Library.Management.System.Project.Repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {

    private BookResponseDTO mapToBookDTO(Book book){
        BookResponseDTO dto=new BookResponseDTO();
        dto.setName(book.getName());
        dto.setBookId(book.getBookId());
        dto.setTotalCopies(book.getTotalCopies());
        dto.setAvailableCopies(book.getAvailableCopies());

        return dto;
    }

    @Autowired
    BookRepo repo;

    @Transactional
    public BookResponseDTO createBook(BookCreateDTO bookDetail){
        if(bookDetail.getAvailableCopies()>bookDetail.getTotalCopies()){
            throw new RuntimeException("Available copies cannnot exceed total copies");
        }
        Book book=new Book();
        book.setName(bookDetail.getName());
        book.setTotalCopies(bookDetail.getTotalCopies());
        book.setAvailableCopies(bookDetail.getAvailableCopies());
        book=repo.save(book);
        book.setBookId("B%03d".formatted(book.getId()));
         return mapToBookDTO(book);
    }

    public List<BookResponseDTO> getBooks() {
        return repo.findAll().
                stream()
                .map(this::mapToBookDTO)
                .toList();
    }

    public BookResponseDTO getBook(String bookId) {
       Book book= repo.findByBookId(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        return mapToBookDTO(book);
    }

    public String deleteBook(String bookId) {
       Book book= repo.findByBookId(bookId).orElseThrow(()->new RuntimeException("Book not found"));
        repo.delete(book);
        return "Book Deleted Successfully";
    }

    @Transactional
    public BookResponseDTO bookUpdate(String bookId, BookUpdateDTO bookDetail) {
        if(bookDetail.getAvailableCopies()>bookDetail.getTotalCopies()){
            throw new RuntimeException("Available copies cannnot exceed total copies");
        }
       Book book=repo.findByBookId(bookId).orElseThrow(()->new RuntimeException("Book not found"));
       book.setName(bookDetail.getName());
       book.setTotalCopies(bookDetail.getTotalCopies());
       book.setAvailableCopies(bookDetail.getAvailableCopies());
        return mapToBookDTO(book);
    }
}
