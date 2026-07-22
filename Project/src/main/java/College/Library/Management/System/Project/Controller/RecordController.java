package College.Library.Management.System.Project.Controller;

import College.Library.Management.System.Project.DTO.CreateRecordDTO;
import College.Library.Management.System.Project.Model.BorrowBook;
import College.Library.Management.System.Project.Service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("records")
public class RecordController {

    @Autowired
    RecordService service;

    @GetMapping
    public List<BorrowBook> getAllRecord(){
        return service.allRecord();
    }

    @GetMapping("/{recordId}")
    public BorrowBook getRecord(@PathVariable Long recordId){
        return service.getRecord(recordId);
    }

    @PostMapping
    public BorrowBook createRecord(@RequestBody CreateRecordDTO record){
        return service.createRecord(record);
    }

    @PutMapping("/{recordId}")
    public BorrowBook updateRecord(
            @PathVariable Long recordId,
            @RequestBody BorrowBook record){
        return service.updateRecord(recordId,record);
    }

    @DeleteMapping("/{recordId}")
    public String deleteRecord(@PathVariable Long recordId){
        return service.deleteRecord(recordId);
    }

}
