package rest.api;
import rest.persistence.entity.User;
import rest.dto.BookDTO;
import rest.dto.RecordDTO;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import java.text.ParseException;

import java.util.UUID;

public interface Api {
    @GetMapping(value = ApiUrls.MAIN) RedirectView redirectToMainPage();
    @GetMapping(value = ApiUrls.OVERVIEW) ModelAndView overview();
    @GetMapping(value = ApiUrls.LOGIN) ModelAndView login();
    @GetMapping(value = ApiUrls.ADD_RECORD) ModelAndView newRecord();
    @PostMapping(value = ApiUrls.ADD_RECORD) void addRecord(RecordDTO recordDTO, @RequestParam("file") MultipartFile file, HttpServletResponse response) throws IOException, InterruptedException;
    @GetMapping void removeRecord(@PathVariable(value = "id") UUID id, HttpServletResponse response, ModelAndView modelAndView) throws IOException;
    @GetMapping(value = ApiUrls.BOOKINGS) ModelAndView bookings();
    @GetMapping(value = ApiUrls.REGISTRATION) ModelAndView registration(ModelAndView model);
    @PostMapping(value = ApiUrls.REGISTRATION) ModelAndView addUser(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, ModelAndView model);
    @GetMapping(value = ApiUrls.BOOK_ID) ModelAndView formBook(@PathVariable(value = "id") UUID id, ModelAndView model, HttpServletRequest request) throws IOException, ParseException;
    @PostMapping(value = ApiUrls.BOOK_ID) void createBook(@PathVariable(value = "id") UUID id, BookDTO bookDTO, HttpServletResponse response) throws ParseException, IOException;
    @GetMapping(value = ApiUrls.BOOK_INFO) ModelAndView bookInfo(@PathVariable(value = "id") Long id, ModelAndView model, HttpServletRequest request) throws ParseException;
}
