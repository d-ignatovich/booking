package rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import rest.service.HtmlPageService;
import rest.dto.RecordDTO;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@RestController
public class Controller {

    @Autowired
    private HtmlPageService htmlPageService;

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
    public ModelAndView addRecord(RecordDTO recordDTO, @RequestParam("file") MultipartFile file){
        recordDTO.setId(UUID.randomUUID().toString());
        if (file.isEmpty()) {
            recordDTO.setImage("default.jpg");
        } else {
            recordDTO.setImage(recordDTO.getId() + ".jpg");
        FileUploadUtility.saveFile(recordDTO.getImage(), file);
        }
        return htmlPageService.createRecord(recordDTO);
    }

    @GetMapping(value = "/remove/{id}")
    public void removeRecord(@PathVariable(value = "id") UUID id, HttpServletResponse response, ModelAndView modelAndView) throws IOException {
        modelAndView.clear();
        htmlPageService.removeRecord(id);
        response.sendRedirect("/");
    }
}
//вверху поменять базы данных