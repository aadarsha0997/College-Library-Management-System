package College.Library.Management.System.Project.Service;

import College.Library.Management.System.Project.DTO.BookCreateDTO;
import College.Library.Management.System.Project.Model.Book;
import College.Library.Management.System.Project.Repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired
    BookRepo repo;

    public BookCreateDTO createBook(BookCreateDTO bookDetail){
        Book book=new Book();
        book.setName(bookDetail.getName());
        book.setTotalCopies(bookDetail.getTotalCopies());
        book.setAvailableCopies(bookDetail.getAvailableCopies());
         repo.save(book);
         return bookDetail;
    }
}
