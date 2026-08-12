package College.Library.Management.System.Project.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginDTO {

    @NotBlank(message = "Please fill the Name field of the book")
    private String studentId;

    @Size(min = 8,message = "Please Choose Strong Password")
    @NotBlank(message = "Please fill the Name field of the book")
    private String password;
}
