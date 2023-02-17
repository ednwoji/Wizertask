package com.interview.task.Wizertask.Controller;


import com.interview.task.Wizertask.Entity.Books;
import com.interview.task.Wizertask.Entity.Category;
import com.interview.task.Wizertask.Entity.Favourites;
import com.interview.task.Wizertask.Service.BookService;
import com.interview.task.Wizertask.Service.CategoryService;
import com.interview.task.Wizertask.Service.FavouriteService;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/project")
@Slf4j
public class ProjectController {

    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private FavouriteService favouriteService;

    @PostMapping("/addcategory")
    public ResponseEntity<Category> addCategory(@RequestBody @NotNull Category category) {

        category.setDateOfAddition(LocalDateTime.now());
        log.info("Received Input and sending to service class");

        categoryService.saveCategory(category);

        return ResponseEntity.ok(category);

    }

    @PostMapping("/addbooks")
    public ResponseEntity<Books> addBooks(@RequestBody Books books) {

        log.info("Received Input and sending to service class");
        bookService.saveBooks(books);
//        return books;
        return ResponseEntity.ok(books);
    }


    @GetMapping("/getbooks")
    public ResponseEntity<List<Books>> listBooks() {

        log.info("Received Input and sending to service class");
        List<Books> books = bookService.getBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/getfavourites")
    public ResponseEntity<List<Favourites>> listFavourites() {

        log.info("Received Input and sending to service class");
        List<Favourites> favy = favouriteService.getFavourites();
        return ResponseEntity.ok(favy);
    }

    @GetMapping("/getcategory")
    public ResponseEntity<List<Category>> listCategories() {

        log.info("Received Input and sending to service class");
        List<Category> category = categoryService.getCategory();
        return ResponseEntity.ok(category);
    }

    @GetMapping("/delete/{bookId}")
    public String deleteBooks(@PathVariable("bookId") Long id) {

        log.info("Trying to delete Book with id "+id);
        bookService.deleteBook(id);
        return "Record Deleted Successfully";
    }

    @GetMapping("/delete/{favId}")
    public String deleteFavourites(@PathVariable("favId") Long id) {

        log.info("Trying to delete favourite with id "+id);
        favouriteService.deleteBook(id);
        return "Record Deleted Successfully";
    }


    @GetMapping("/delete/{categoryId}")
    public String deleteCategory(@PathVariable("categoryId") Long id) {

        log.info("Trying to delete Category with id "+id);
        categoryService.deleteCategory(id);
        return "Category Record Deleted Successfully";
    }


    @GetMapping("book/{id}")
    public ResponseEntity<Optional<Books>> findByBookID(@PathVariable("id") Long id) {

        log.info("Searching by ID");
        Optional<Books> books = bookService.findByID(id);
        return ResponseEntity.ok(books);
    }

    @GetMapping("category/{id}")
    public ResponseEntity<Optional<Category>> findByCategoryID(@PathVariable("id") Long id) {

        log.info("Searching by Category ID");
        Optional<Category> category = categoryService.findByID(id);
        return ResponseEntity.ok(category);
    }

    public Favourites addFavourites(@RequestBody Favourites favourites) {

        log.info("Adding Books to Favourites");
        favouriteService.addFavourites(favourites);
        return favourites;
    }



}
