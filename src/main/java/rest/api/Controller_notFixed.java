package rest.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import rest.dto.StudentDto;
import rest.persistence.entity.User;
import rest.service.StudentService;
import rest.service.UserService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * Класс в котором описываются http методы (АПИ), методы вызываются с фронта
 **/
@RestController
public class Controller implements Api {
    @Autowired
    private StudentService studentService;

    @Autowired
    private UserService userService;

    @Override
    public ModelAndView welcome() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("welcome"); // указываю какую страницу вернуть
        modelAndView.getModel().put("message", "ПРИВЕТ asdasdasdasdasd"); // иньекция значений на страницу
        return modelAndView;
    }
    @Override
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @Override
    public ModelAndView showStudents() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.clear();
        modelAndView.getModel().put("listStudents", studentService.getAllStudents());
        modelAndView.setViewName("studets-page");
        return modelAndView;
    }

    @Override
    public ModelAndView newStudent() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("new-student"); // указываю какую страницу вернуть
        return modelAndView;
    }
    @Override
    public ModelAndView addStudent(StudentDto studentDto) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.clear();
        modelAndView.getModel().put("listStudents", studentService.createStudent(studentDto));
        modelAndView.setViewName("studets-page");
        return modelAndView;
    }
    @Override
    public void removeStudent(@PathVariable(value = "id") UUID id, HttpServletResponse response, ModelAndView modelAndView) throws IOException {
        modelAndView.clear();
        studentService.removeStudentById(id);
        response.sendRedirect("/all-students");
    }

    @GetMapping("/")
    public RedirectView redirectToWelcomePage() {
        return new RedirectView("/welcome");
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
        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())) {
            model.getModel().put("passwordError", "Пароли не совпадают");
            return model;
        }
        if (!userService.saveUser(userForm)) {
            model.getModel().put("usernameError", "Пользователь с таким именем уже существует");
            return model;
        }

        model.clear();

        model.clear();
        model.setView(new RedirectView("/login"));
        return model;
    }

}
