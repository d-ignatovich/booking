package rest.api;
import rest.dto.BookDTO;
import rest.dto.RecordDTO;
import rest.persistence.entity.Book;
import rest.persistence.entity.Record;
import rest.persistence.entity.User;
import rest.persistence.repository.BookRepository;
import rest.persistence.repository.RecordRepository;
import rest.service.RecordService;
import rest.service.UserService;
import rest.service.BookService;
import rest.service.FileUploadService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@RestController
public class Controller implements Api {
    @Autowired
    private RecordService recordService;

    @Autowired
    private UserService userService;

    @Autowired
    private FileUploadService fileUploadService;

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private RecordRepository recordRepository;

    public User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

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
        response.sendRedirect("/{id}");
    }



    @Override
    public ModelAndView bookings() {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.clear();
            modelAndView.setViewName("bookings");
            modelAndView.getModel().put("booksByRecord", bookService.getAllBooksByRecord(getCurrentUser().getId()));
            return modelAndView;
        }


    @Override
    public RedirectView redirectToMainPage() {
        return new RedirectView("/overview");
    }

    @Override
    public ModelAndView registration(ModelAndView model) {
        model.setViewName("registration");
        model.getModel().put("userForm", new User());
        return model;
    }

    @Override
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

    @Override
    public ModelAndView formBook(@PathVariable(value = "id") UUID id, ModelAndView model, HttpServletRequest request) throws IOException, ParseException {
        Record record = recordRepository.findById(id).get();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat form = new SimpleDateFormat("dd/MM/yyyy");
        Date start = sdf.parse(request.getParameter("start"));
        Date end = sdf.parse(request.getParameter("end"));
        String berth = request.getParameter("berth");
        long daysBetween = TimeUnit.MILLISECONDS.toDays(end.getTime() - start.getTime());
        model.setViewName("book");
        model.getModel().put("record", record);
        model.getModel().put("start", form.format(start));
        model.getModel().put("end", form.format(end));
        model.getModel().put("daysBetween", daysBetween);
        model.getModel().put("people", berth);
        return model;
    }

    @Override
    public void createBook(@PathVariable(value = "id") UUID id, BookDTO bookDTO, HttpServletResponse response) throws ParseException, IOException {
        Record record = recordRepository.findById(id).get();
        bookDTO.setId(UUID.randomUUID().toString());
        bookService.createBook(bookDTO, getCurrentUser(), record);
        response.sendRedirect("/");
    }

    @Override
    public ModelAndView bookInfo(@PathVariable(value = "id") Long id, ModelAndView model, HttpServletRequest request) throws ParseException {
        Book book = bookRepository.findById(id).get();
        Record record = book.getRecord();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat form = new SimpleDateFormat("dd/MM/yyyy");
        Date start = sdf.parse(book.getDate_start().toString());
        Date end = sdf.parse(book.getDate_end().toString());
        long daysBetween = TimeUnit.MILLISECONDS.toDays(end.getTime() - start.getTime());
        model.clear();
        model.setViewName("bookinfo");
        model.getModel().put("record", record);
        model.getModel().put("book", book);
        model.getModel().put("start", form.format(start));
        model.getModel().put("end", form.format(end));
        model.getModel().put("daysBetween", daysBetween);
        return model;
    }

}
