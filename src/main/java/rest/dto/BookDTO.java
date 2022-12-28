package rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {

    private UUID id;
    private String name;
    private Integer people;
    private Date date_start;
    private Date date_finish;
    private Integer phone;
    private String userId;
    private String record;
}
