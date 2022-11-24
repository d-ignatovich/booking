package rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import rest.service.HtmlPageService;
import rest.dto.RecordDTO;

import javax.servlet.http.HttpServletResponse;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Класс в котором описываются http методы (АПИ), методы вызываются с фронта
 **/
@RestController
public class Controller {

    @Autowired
    private HtmlPageService htmlPageService;

    /**
     * Пример метода который будет вызываться по URL: http://localhost:8080
     * метод возвращает html страницу, "resources/templates/welcome.html"
     **/
    @GetMapping(value = "/")
    public ModelAndView recordsPage() {
        ModelAndView modelAndView = new ModelAndView();
        return htmlPageService.createRecordPage();
    }

    @GetMapping(value = "/create")
    public ModelAndView newRecord() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("create"); // указываю какую страницу вернуть
        return modelAndView;
    }

    @PostMapping(value = "/create")
    public ModelAndView addRecord(RecordDTO recordDTO) {
        return htmlPageService.createRecord(recordDTO);
    }

    @GetMapping(value = "/remove/{id}")
    public void removeRecord(@PathVariable(value = "id") UUID id, HttpServletResponse response, ModelAndView modelAndView) throws IOException {
        modelAndView.clear();
        htmlPageService.removeRecord(id);
        response.sendRedirect("/");
    }
}
