package com.interview.task.Wizertask.Service;

import com.interview.task.Wizertask.Entity.Books;
import com.interview.task.Wizertask.Repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void saveBooks(Books books) {

        log.info("Saving Category");
        bookRepository.save(books);
    }

    @Override
    public List<Books> getBooks() {

       return bookRepository.findAll();

    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Optional<Books> findByID(Long id) {
        return bookRepository.findById(id);
    }
}
