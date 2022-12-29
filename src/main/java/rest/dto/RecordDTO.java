package rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecordDTO {

    private String id;
    private String title;
    private String address;
    private Integer berth;
    private Integer rent;
    private String description;
    private String image;
    private String userId;
}
