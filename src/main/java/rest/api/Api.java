package rest.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import rest.dto.StudentDto;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

public interface Api {
    /**
     * Пример метода который будет вызываться по URL: http://localhost:8080
     * метод возвращает html страницу, "resources/templates/welcome.html"
     **/
    @GetMapping(value = ApiUrls.WELCOME) ModelAndView welcome();
    @GetMapping(value = ApiUrls.LOGIN) ModelAndView login();


    /**
     * Пример метода который будет вызываться по URL: http://localhost:8080/select/{имя_таблицы_в_которой_хотите_посмотреть_данные}
     * метод возвращает html страницу, "resources/templates/show_table.html"
     **/
    @GetMapping(value = ApiUrls.GET_ALL_STUDENT) ModelAndView showStudents();
    @GetMapping(value = ApiUrls.ADD_STUDENT) ModelAndView newStudent();
    @PostMapping(value = ApiUrls.ADD_STUDENT) ModelAndView addStudent(StudentDto studentDto);
    @GetMapping(value = ApiUrls.REMOVE_STUDENT) void removeStudent(@PathVariable(value = "id") UUID id, HttpServletResponse response, ModelAndView modelAndView) throws IOException;
}
