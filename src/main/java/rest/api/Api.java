package rest.api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import rest.dto.RecordDTO;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

public interface Api {
    @GetMapping(value = ApiUrls.OVERVIEW) ModelAndView overview();
    @GetMapping(value = ApiUrls.LOGIN) ModelAndView login();
    @GetMapping(value = ApiUrls.ADD_RECORD) ModelAndView newRecord();
    @PostMapping(value = ApiUrls.ADD_RECORD) void addRecord(RecordDTO recordDTO, @RequestParam("file") MultipartFile file, HttpServletResponse response) throws IOException, InterruptedException;

    @GetMapping void removeRecord(@PathVariable(value = "id") UUID id, HttpServletResponse response, ModelAndView modelAndView) throws IOException;

    @GetMapping(value = ApiUrls.BOOKINGS) ModelAndView bookings();
}
