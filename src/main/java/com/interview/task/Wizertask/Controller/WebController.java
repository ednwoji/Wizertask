package com.interview.task.Wizertask.Controller;


import com.interview.task.Wizertask.Entity.Books;
import com.interview.task.Wizertask.Entity.Category;
import com.interview.task.Wizertask.Entity.Favourites;
import com.interview.task.Wizertask.Repository.BookRepository;
import com.interview.task.Wizertask.Repository.CategoryRepository;
import com.interview.task.Wizertask.Repository.FavouritesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/web")
@Slf4j
public class WebController {

    @Autowired
    private ProjectController projectController;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private FavouritesRepository favouritesRepository;


    @GetMapping("/home")
    public String homePage(Model model) {

        List<Category> category = projectController.listCategories().getBody();

        System.out.println(category);
        model.addAttribute("category", category);

        return "Home";
    }

    @GetMapping("/favourites")
    public String viewFavourites(Model model) {

        List<Favourites> favourites = projectController.listFavourites().getBody();
        model.addAttribute("books", favourites);
        return "Favourites";
    }


    @GetMapping("/books")
    public String viewBooks(Model model) {

        List<Books> books = projectController.listBooks().getBody();

        System.out.println(books);
        model.addAttribute("books", books);

        return "Books";
    }

    @GetMapping("/create-book")
    public String showCreateForm(Books books, Model model){

        List<Category> category = projectController.listCategories().getBody();

        System.out.println(category);
        model.addAttribute("category", category);

        return "AddBooks";
    }

    @GetMapping("/create-category")
    public String createCategoryForm(Category category, Model model){

        return "AddCategory";
    }

    @PostMapping("/addbooks")
    public String addBooks(Books books, RedirectAttributes redirectAttributes) {

       Books books1 = projectController.addBooks(books).getBody();
//       HttpStatus value = responseEntity.getStatusCode();
       System.out.println(books1);

       redirectAttributes.addFlashAttribute("success", "Record Entered Successfully");

        return "redirect:/web/create-book";
    }

    @PostMapping("/addcategory")
    public String addCategory(Category category, RedirectAttributes redirectAttributes) {

        category.setDateOfAddition(LocalDateTime.now());
        Category category1 = projectController.addCategory(category).getBody();
        System.out.println(category1);

        redirectAttributes.addFlashAttribute("success", "Record Entered Successfully");

        return "redirect:/web/create-category";
    }


    @GetMapping("/deletecategory/{id}")
    public String deleteCategory(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {

        projectController.deleteCategory(id);
        redirectAttributes.addFlashAttribute("success", "Record Deleted Successfully");
        return "redirect:/web/home";

    }

    @GetMapping("/deletebooks/{id}")
    public String deleteBooks(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {

        projectController.deleteBooks(id);
        redirectAttributes.addFlashAttribute("success", "Record Deleted Successfully");
        return "redirect:/web/books";

    }

    @GetMapping("/deletefavourites/{id}")
    public String deleteFavourites(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {

        projectController.deleteFavourites(id);
        redirectAttributes.addFlashAttribute("success", "Favourite removed successfully");
        return "redirect:/web/favourites";

    }

    @GetMapping("/edit/{id}")
    public String displayBooksEditPage(@PathVariable("id") Long id, Model model) {

        Optional<Books> editBooks = projectController.findByBookID(id).getBody();
        System.out.println(editBooks);
        List<Category> category = projectController.listCategories().getBody();
        model.addAttribute("category", category);
        model.addAttribute("fetchedbooks", editBooks);

        return "EditBooks";

    }

    @GetMapping("/editcategory/{id}")
    public String displayCategoryEditPage(@PathVariable("id") Long id, Model model) {

        Optional<Category> editCategory = projectController.findByCategoryID(id).getBody();
        System.out.println(editCategory);
        model.addAttribute("fetchedcategory", editCategory);
        return "EditCategory";
    }


    @PostMapping("/updatebooks")
    public String UpdateBooks(Books books, RedirectAttributes redirectAttributes) {
        bookRepository.save(books);
        redirectAttributes.addFlashAttribute("success", "Book updated successfully");
        return "redirect:/web/books";

    }

    @PostMapping("/updatecategory")
    public String UpdateCategory(Category category, RedirectAttributes redirectAttributes) {
        category.setDateOfAddition(LocalDateTime.now());
        categoryRepository.save(category);
        redirectAttributes.addFlashAttribute("success", "Category updated successfully");
        return "redirect:/web/home";

    }



    @GetMapping("/favourites/{id}")
    public String addFavourites(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        log.info("Adding to favourites");
        Optional<Books> books = bookRepository.findById(id);
        Favourites favourites = new Favourites(books.get().getBookId(), books.get().getBookName(), books.get().getAuthor(),books.get().getCategoryName(), books.get().getYearOfRelease());
        favouritesRepository.save(favourites);
        redirectAttributes.addFlashAttribute("success", "Book "+books.get().getBookName()+ " Added to Favourites");


        return "redirect:/web/books";


    }

}
