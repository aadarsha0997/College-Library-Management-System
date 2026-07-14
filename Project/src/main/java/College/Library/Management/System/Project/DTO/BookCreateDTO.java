package College.Library.Management.System.Project.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BookCreateDTO {

    @NotBlank(message = "Please fill the Name field of the book")
    String name;

    @NotNull(message = "Please enter the total stock")
    @Min(value = 0,message = "Stock can not be less then zero")
    Integer totalCopies;

    @NotNull(message = "Please enter the total Available stock")
    @Min(value = 0,message = "Stock can not be less then zero")
    Integer availableCopies;
}
