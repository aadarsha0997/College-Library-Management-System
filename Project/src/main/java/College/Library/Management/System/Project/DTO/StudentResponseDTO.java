package College.Library.Management.System.Project.DTO;

import lombok.Data;

@Data
public class StudentResponseDTO {
    private String name;
    private String studentId;
    private  String faculty;
    private int semester;
    private String phoneNumber;
}
