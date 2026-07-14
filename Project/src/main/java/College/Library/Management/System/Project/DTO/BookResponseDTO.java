package College.Library.Management.System.Project.DTO;

import lombok.Data;

@Data
public class BookResponseDTO {
    private String name;
    private String bookId;

    private Integer totalCopies;
    private Integer availableCopies;
}
