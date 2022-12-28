package rest.service;

import org.springframework.stereotype.Service;

import rest.dto.BookDTO;
import rest.persistence.entity.Book;
import rest.persistence.entity.Record;
import rest.persistence.entity.User;
import rest.persistence.repository.BookRepository;

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

    public List<BookDTO> createBook(BookDTO bookDTO, User user, Record record) {
        Book book = new Book();
        book.setId(UUID.fromString(bookDTO.getId().toString()));
        book.setName(bookDTO.getName());
        book.setPeople(bookDTO.getPeople());
        book.setDate_start(bookDTO.getDate_start());
        book.setDate_finish(bookDTO.getDate_finish());
        book.setPhone(bookDTO.getPhone());
        book.setUser(user);
        book.setRecord(record);
        bookRepository.save(book);
        return getAllBooks();
    }

    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.getAllBooks();
        List<BookDTO> resultList = new ArrayList<>();
        for (Book book : books) {
            BookDTO bookDTO = new BookDTO();
            bookDTO.setId(UUID.fromString(book.getId().toString()));
            bookDTO.setName(book.getName());
            bookDTO.setPeople(book.getPeople());
            bookDTO.setDate_start(book.getDate_start());
            bookDTO.setDate_finish(book.getDate_finish());
            bookDTO.setPhone(book.getPhone());
            bookDTO.setUserId(book.getUser().getId().toString());
            bookDTO.setRecord(book.getRecord().toString());
            resultList.add(bookDTO);
        }
        return resultList;
    }

    public void findBookById(UUID id) {
        Optional<Book> book = bookRepository.findById(id);
        ArrayList<Book> res = new ArrayList<>();
        book.ifPresent(res :: add);
    }

    public void removeBookById(UUID id) {
    }
}
