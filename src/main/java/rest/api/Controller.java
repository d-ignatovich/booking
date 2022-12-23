package rest.api;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import rest.dto.RecordDTO;
import rest.persistence.entity.User;
import rest.service.RecordService;
import rest.service.UserService;
import rest.service.FileUploadService;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

@RestController
public class Controller implements Api {
    @Autowired
    private RecordService recordService;

    @Autowired
    private UserService userService;

    @Autowired
    private FileUploadService fileUploadService;

    Logger logger = LoggerFactory.getLogger(Controller.class);

    @Override
    public ModelAndView overview() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.clear();
        modelAndView.setViewName("overview");
        modelAndView.getModel().put("records", recordService.getAllRecords());
        modelAndView.getModel().put("userId", getCurrentUser().getId().toString());
        return modelAndView;
    }

    
    @Override
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @Override
    public ModelAndView newRecord() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("create"); // указываю какую страницу вернуть
        return modelAndView;
    }

    @Override
    public void addRecord(RecordDTO recordDTO, @RequestParam("file") MultipartFile file, HttpServletResponse response) throws IOException, InterruptedException {
        recordDTO.setId(UUID.randomUUID().toString());
        if (file.isEmpty()) {
            recordDTO.setImage("default.jpg");
        } else {
            recordDTO.setImage(UUID.randomUUID().toString() + ".jpg");
            FileUploadService.saveFile(recordDTO.getImage(), file);
        }
        recordService.createRecord(recordDTO, getCurrentUser());
        response.sendRedirect("/");
    }
    
    @Override
    public void removeRecord(@PathVariable(value = "id") UUID id, HttpServletResponse response, ModelAndView modelAndView) throws IOException {
        modelAndView.clear();
        recordService.removeRecordById(id);;
        response.sendRedirect("/");
    }

    @GetMapping("/")
    public RedirectView redirectToWelcomePage() {
        return new RedirectView("/overview");
    }

    @GetMapping("/registration")
    public ModelAndView registration(ModelAndView model) {
        model.setViewName("registration");
        model.getModel().put("userForm", new User());
        return model;
    }

    @PostMapping("/registration")
    public ModelAndView addUser(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, ModelAndView model) {
        model.setViewName("registration");
        if (bindingResult.hasErrors()) {
            return model;
        }
        if (userService.findUserByEmail(userForm) != null) {
            model.getModel().put("usernameError", "Пользователь с такой почтой уже существует.");
            return model;
        }
        else if (userService.allUsers().stream().filter(user -> user.getPhone().equals(userForm.getPhone())).findFirst().orElse(null) != null) {
            model.getModel().put("phoneError", "Пользователь с таким номером телефона уже существует.");
            return model;
        }
        else {
            userService.saveUser(userForm);
        }

        model.clear();
        model.setView(new RedirectView("/login"));
        return model;
    }

    public User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
