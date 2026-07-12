package College.Library.Management.System.Project.DTO;


import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class StudentCreateDTO {

    @NotBlank(message="Name is Required")
    @Size(min = 3,max = 50, message = "Name must be between 3 to 50 character")
    private String name;

    @NotBlank(message="Password is Required")
    @Size(min = 8,message = "Password must be mre then 8 character")
    private String password;

    @NotBlank(message = "Faculty is required")
    private   String faculty;

    @NotNull(message = "Semester is required")
    @Min(value = 1, message = "Semester must be at least 1")
    @Max(value = 8, message = "Semester cannot be greater than 8")
    private int semester;

    @NotBlank(message = "Phone number is required")
    @Pattern(
            regexp = "^[0-9]{10}$",
            message = "Invalid Phone Number")
    private String phoneNumber;
}
