package com.example.Practice.controller;

import com.example.Practice.entity.Book;
import com.example.Practice.entity.User;
import com.example.Practice.exception.IdNotFoundException;
import com.example.Practice.exception.UserAlreadyPresentException;
import com.example.Practice.repository.bookRepo;
import com.example.Practice.repository.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class libraryController {
    @Autowired
    private bookRepo repoBook;

    @Autowired
    private userRepo repoUser;

    @PostMapping("/add")
    public ResponseEntity<User> addDetails(@RequestBody User user) throws UserAlreadyPresentException{
        Optional<User> users = repoUser.findById(user.getId());
        if(users.isPresent()){
            throw new UserAlreadyPresentException("This user with id " + user.getId() + "is already present");
        }
        return ResponseEntity.ok().body(repoUser.save(user));
    }
    @PostMapping("/addBook")
    public ResponseEntity<Book> addDetails(@RequestBody Book book){
        return ResponseEntity.ok().body(repoBook.save(book));
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserDetails(@PathVariable int id ) {
        Optional<User> users = repoUser.findById(id);
        if(users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(repoUser.findById(id).orElse(null));

    }

    @GetMapping("/book/{id}")
    public ResponseEntity<Book> getBookDetails(@PathVariable int id ) throws IdNotFoundException {
        Optional<Book> buk = repoBook.findById(id);
        if (buk.isEmpty()) {
            throw new IdNotFoundException("id not found");
        } else {
            return ResponseEntity.ok().body(repoBook.findById(id).orElse(null));
        }

    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Book> updateDetails(@RequestBody Book book, @PathVariable int id){
        Book buk = null;
        Optional<Book> updatebook = repoBook.findById(id);
        if(updatebook.isPresent()){
            buk = updatebook.get();
            buk.setId(id);
            buk.setBookName(book.getBookName());
            repoBook.save(buk);
            return ResponseEntity.ok().body(buk);
        }
        else{
            return ResponseEntity.noContent().build();
        }}

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteBook(@PathVariable int id){
        Optional<Book> buk = repoBook.findById(id);
        if(buk.isEmpty()){
            return ResponseEntity.ok(false);
        }
        else{
            repoBook.deleteById(id);
            return ResponseEntity.ok(true);
        }
    }





}
