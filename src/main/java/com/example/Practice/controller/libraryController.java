package com.example.Practice.controller;

import com.example.Practice.entity.Book;
import com.example.Practice.entity.User;
import com.example.Practice.repository.bookRepo;
import com.example.Practice.repository.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class libraryController {
    @Autowired
    private bookRepo repoBook;

    @Autowired
    private userRepo repoUser;

    @PostMapping("/add")
    public ResponseEntity<User> addDetails(@RequestBody User user){
        return ResponseEntity.ok().body(repoUser.save(user));
    }
    @PostMapping("/addBook")
    public ResponseEntity<Book> addDetails(@RequestBody Book book){
        return ResponseEntity.ok().body(repoBook.save(book));
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserDetails(@PathVariable int id ){
        return ResponseEntity.ok().body(repoUser.findById(id).orElse(null));
    }
    @GetMapping("/book/{id}")
    public ResponseEntity<Book> getBookDetails(@PathVariable int id ){
        return ResponseEntity.ok().body(repoBook.findById(id).orElse(null));
    }

}
