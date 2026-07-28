package College.Library.Management.System.Project.DTO;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data

public class ReturnRecordDTO {
    private Long recordId;
    private LocalDate borrowAt;
    private LocalDate returnAt;
    private String student;
    private String studentId;
    private String book;
    private String bookId;

}
