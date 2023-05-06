package com.example.Practice.repository;

import com.example.Practice.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface bookRepo extends JpaRepository<Book,Integer> {

}
