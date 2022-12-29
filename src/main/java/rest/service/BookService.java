package rest.service;
import rest.dto.BookDTO;
import rest.persistence.repository.BookRepository;
import rest.persistence.entity.Book;
import rest.persistence.entity.Record;
import rest.persistence.entity.User;
import rest.persistence.repository.RecordRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.var;

@Service
public class BookService {

    @Autowired
    private RecordRepository recordRepository;
    
    @Autowired
    private BookRepository bookRepository;

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


    public Map<Record,List<BookDTO>> getAllBooksByRecord(long currentUserId) {
        return recordRepository.findAll().stream()
                .collect(Collectors.toMap(
                        record -> record,
                        record -> bookRepository.findAll().stream()
                                .filter(book -> book.getRecord().equals(record) && book.getUser().getId()==currentUserId)
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
                                .collect(Collectors.toList())))
                .entrySet().stream()
                .filter(booksByRecord->booksByRecord.getValue().size()>0)
                .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));

    }
}