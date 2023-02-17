package com.interview.task.Wizertask.Service;


import com.interview.task.Wizertask.Entity.Books;

import java.util.List;
import java.util.Optional;

public interface BookService {
    void saveBooks(Books books);

    List<Books> getBooks();

    void deleteBook(Long id);

    Optional<Books> findByID(Long id);
}
