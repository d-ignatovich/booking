package rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {

    private String id;
    private String name;
    private String people;
    private String date_start;
    private String date_end;
    private String phone;

}
