package com.java.books.controller;

import com.java.books.entity.Book;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BooksController {

    private final List<Book> books = new ArrayList<>();


    public BooksController() {
        initilizeBooks();
    }

    private void initilizeBooks() {
        books.addAll(List.of(
                new Book(1, "On the Origin of Species", "Charles Darwin", "Science", 5),
                new Book(2, "The Voyage of the Beagle", "Charles Darwin", "Science", 4),
                new Book(3, "Principia Mathematica", "Isaac Newton", "Science", 5),
                new Book(4, "Dialogue Concerning the Two Chief World Systems", "Galileo Galilei", "Science", 4),
                new Book(5, "The Selfish Gene", "Richard Dawkins", "Science", 4),
                new Book(6, "The Double Helix", "James D. Watson", "Science", 4)
        ));

    }


    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable long  id)
    {
       books.removeIf( b-> b.getId() == id);
    }


    @PutMapping("/{id}")
    public void updateBook(@PathVariable long id, @RequestBody Book bookToUpdate)
    {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == id) {
                books.set(i, bookToUpdate);
                return;
            }
        }
    }


    @PostMapping
    public void createBook(@RequestBody Book  newBook)
    {
        for (Book b : books) {
            if (b.getTitle().equalsIgnoreCase(newBook.getTitle())) {
                return;
            }
        }

        books.add(newBook);
    }


    @GetMapping("/{id}")
    public Book getBookById(@PathVariable long id) {
        for (Book b : books) {
            if (b.getId() == id) {
                return b;
            }
        }

        return null;
    }

    @GetMapping
    public List<Book> getBooks() {

        return books;
    }
}
