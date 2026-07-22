package College.Library.Management.System.Project.Service;

import College.Library.Management.System.Project.DTO.CreateRecordDTO;
import College.Library.Management.System.Project.Model.Book;
import College.Library.Management.System.Project.Model.BorrowBook;
import College.Library.Management.System.Project.Model.Student;
import College.Library.Management.System.Project.Repo.BookRepo;
import College.Library.Management.System.Project.Repo.RecordRepo;
import College.Library.Management.System.Project.Repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RecordService {

    @Autowired
    RecordRepo repo;

    @Autowired
    StudentRepo studentRepo;
    @Autowired
    BookRepo bookRepo;

    public String allRecord(){
        return "allRecord";
    }

    public String getRecord(Long recordId) {
        return "single";
    }

    public String deleteRecord(Long recordId) {
        return "delete";
    }

    public BorrowBook createRecord(CreateRecordDTO record) {
        Student student = studentRepo.findByStudentId(record.getStudentId()).orElseThrow();
        Book book= bookRepo.findByBookId(record.getBookId()).orElseThrow();

        BorrowBook data=new BorrowBook();

        data.setStudent(student);
        data.setBook(book);
        data.setBorrowAt(LocalDate.now());
        return repo.save(data);
    }

    public  BorrowBook updateRecord(Long recordId, BorrowBook record) {
        return record;
    }
}
