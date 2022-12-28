package rest.service;

import org.springframework.stereotype.Service;

import rest.dto.BookDTO;
import rest.dto.RecordDTO;
import rest.persistence.repository.BookRepository;
import rest.persistence.repository.RecordRepository;
import rest.persistence.entity.Book;
import rest.persistence.entity.Record;
import rest.persistence.entity.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void createBook(BookDTO bookDTO, User user, Record record) throws ParseException {
        SimpleDateFormat form = new SimpleDateFormat("dd/MM/yyyy");
        Book book = new Book();
        book.setName(bookDTO.getName());
        book.setPeople(Integer.valueOf(bookDTO.getPeople()));
        book.setPhone(bookDTO.getPhone());
        book.setDate_start(form.parse(bookDTO.getDate_start()));
        book.setDate_end(form.parse(bookDTO.getDate_end()));
        book.setUser(user);
        book.setRecord(record);
        bookRepository.save(book);
    }
}