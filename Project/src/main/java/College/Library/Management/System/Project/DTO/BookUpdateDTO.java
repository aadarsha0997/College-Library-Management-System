package College.Library.Management.System.Project.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BookUpdateDTO {
    @NotBlank(message = "Please enter the book name")
    @Size(max = 20,message = "Name of book cannot be more the 20 character")
   private String name;

    @NotNull(message = "Please enter to total copies you have")
    @Min(value = 0,message = "Copies cannot be less then zero")
    private Integer totalCopies;

    @NotNull(message = "Please enter to available copies you have")
    @Min(value = 0,message = "Copies cannot be less then zero")
    private Integer availableCopies;
}
