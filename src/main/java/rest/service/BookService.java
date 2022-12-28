package rest.service;

import org.springframework.stereotype.Service;

import rest.dto.BookDTO;
import rest.persistence.repository.BookRepository;
import rest.persistence.entity.Book;
import rest.persistence.entity.Record;
import rest.persistence.entity.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<BookDTO> getAllBooks() {
        var books = bookRepository.findAll();
        return books.stream()
                .map(book -> {
                    var BookDTO = new BookDTO();
                    BookDTO.setId(book.getId().toString());
                    BookDTO.setPhone(book.getPhone());
                    BookDTO.setName(book.getName());
                    BookDTO.setPeople(book.getPeople().toString());
                    BookDTO.setDate_start(book.getDate_start().toString());
                    BookDTO.setDate_end(book.getDate_end().toString());
                    return BookDTO;
                })
                .collect(Collectors.toList());

    }
}