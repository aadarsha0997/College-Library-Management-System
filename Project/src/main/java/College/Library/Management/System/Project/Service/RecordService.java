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
import java.util.List;

@Service
public class RecordService {

    @Autowired
    RecordRepo repo;

    @Autowired
    StudentRepo studentRepo;
    @Autowired
    BookRepo bookRepo;

    public List<BorrowBook> allRecord(){
        return repo.findAll();
    }

    public BorrowBook getRecord(Long recordId) {
        return repo.findById(recordId).orElseThrow();
    }

    public String deleteRecord(Long recordId) {
        repo.deleteById(recordId);
        return "delete Sucessfull ";
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
